package com.wx.watersupplierservice.service.impl;

import com.wx.watersupplierservice.dao.*;
import com.wx.watersupplierservice.dto.OrderDto;
import com.wx.watersupplierservice.dto.UserShopDto;
import com.wx.watersupplierservice.dto.UseroOrderPageDto;
import com.wx.watersupplierservice.dto.WatersPageDto;
import com.wx.watersupplierservice.enums.PlatformStatusEnum;
import com.wx.watersupplierservice.enums.UserRoleEnum;
import com.wx.watersupplierservice.exception.PublicException;
import com.wx.watersupplierservice.po.*;
import com.wx.watersupplierservice.pojo.SysOrgPojo;
import com.wx.watersupplierservice.pojo.SysSitePojo;
import com.wx.watersupplierservice.req.OrderListReq;
import com.wx.watersupplierservice.req.SendWatersReq;
import com.wx.watersupplierservice.service.BusinessService;
import com.xdf.pscommon.mybatis.rt.PMLO;
import com.xdf.pscommon.mybatis.rt.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program: BusinessServiceImpl
 *
 * @description:
 *
 * @author: weili
 *
 * @create: 2021-01-11 23:06
 **/
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private SysShopSiteDao sysShopSiteDao;

    @Autowired
    private SysShopUserDao sysShopUserDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private WaterOrderDao waterOrderDao;

    @Autowired
    private SysOrgUserDao sysOrgUserDao;

    private OrderBusinessDao orderBusinessDao;

    @Override
    public WatersPageDto getSendWaterList(SendWatersReq sendWatersReq) {
        if (Objects.isNull(sendWatersReq) || Objects.isNull(sendWatersReq.getPageIndex())
                || Objects.isNull(sendWatersReq.getSiteId()) || Objects.isNull(sendWatersReq.getPageSize())){
            throw new PublicException("参数不全...");
        }
        WatersPageDto watersPageDto = new WatersPageDto();
        int offset = (sendWatersReq.getPageIndex()-1) * sendWatersReq.getPageSize();
        sendWatersReq.setOffset(offset);
        int count = sysShopSiteDao.getSendWaterCount(sendWatersReq);
        watersPageDto.setPageIndex(sendWatersReq.getPageIndex());
        watersPageDto.setPageSize(sendWatersReq.getPageSize());
        watersPageDto.setCount(count);
        if(count == 0){
           return watersPageDto;
        }
        List<WatersPageDto.WatersDto> list = sysShopSiteDao.getSendWaterList(sendWatersReq);
        watersPageDto.setList(list);
        return watersPageDto;
    }

    @Override
    public UseroOrderPageDto getOrderList(OrderListReq orderListReq) {
        if (Objects.isNull(orderListReq) || Objects.isNull(orderListReq.getUserId())
                || Objects.isNull(orderListReq.getPageIndex()) || Objects.isNull(orderListReq.getPageSize())
                || Objects.isNull(orderListReq.getPlatform())){
            throw new PublicException("参数不全...");
        }
        //根据用户角色返回不同数据
        SysUserPo user = sysUserDao.findById(SysUserPo.class, orderListReq.getUserId());
        UseroOrderPageDto useroOrderPageDto = new UseroOrderPageDto();
        useroOrderPageDto.setPageIndex(orderListReq.getPageIndex());
        useroOrderPageDto.setPageSize(orderListReq.getPageSize());
        if (Objects.isNull(user) || 1 != user.getUserStatus()){
            throw new PublicException("员工已离职");
        }
        if (UserRoleEnum.isORG_MANAGER(user.getRoleCode())){
            useroOrderPageDto = getOrgOrderList(orderListReq,useroOrderPageDto);
        }else if(UserRoleEnum.isSHOP_MANAGER(user.getRoleCode())){
            useroOrderPageDto = getShopOrderList(orderListReq,useroOrderPageDto);
        }else if(UserRoleEnum.isSITE_MANAGER(user.getRoleCode())){
            useroOrderPageDto = getSiteOrderList(orderListReq,useroOrderPageDto);
        }else if(UserRoleEnum.isSITE_SENDER(user.getRoleCode())){
            useroOrderPageDto = getSenderOrderList(orderListReq,useroOrderPageDto);
        }else {
            throw new PublicException("用户角色异常");
        }
        return useroOrderPageDto;
    }

    private UseroOrderPageDto getSenderOrderList(OrderListReq orderListReq, UseroOrderPageDto useroOrderPageDto) {
        useroOrderPageDto.setCount(0);
        return useroOrderPageDto;
    }

    private UseroOrderPageDto getSiteOrderList(OrderListReq orderListReq, UseroOrderPageDto useroOrderPageDto) {
        //获取门店基本信息
        List<SysSitePojo> userShopSiteList = sysShopSiteDao.getSiteList(orderListReq.getUserId());
        if (CollectionUtils.isEmpty(userShopSiteList)){
            throw new PublicException("用户角色异常,该用户非水站");
        }
        if (Objects.nonNull(orderListReq.getPlatform())){
            userShopSiteList = userShopSiteList.stream().filter(v -> Objects.equals(v.getPlatform(), orderListReq.getPlatform())).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(userShopSiteList)){
            throw new PublicException("该用户权限不足");
        }
        List<Integer> siteids = userShopSiteList.stream().map(SysSitePojo::getSiteId).collect(Collectors.toList());
        //根据商户code和平台类型查询订单
        if (PlatformStatusEnum.isPLANTFORM_JD(orderListReq.getPlatform())){
            List<QueryFilter> qfs = new ArrayList<>();
            qfs.add(new QueryFilter("site_id", PMLO.IN, siteids));
            if (Objects.nonNull(orderListReq.getStatus())){
                qfs.add(new QueryFilter("opt_code", orderListReq.getStatus()));
            }
            Integer count = orderBusinessDao.findCount(OrderBusinessPo.class, qfs.toArray(new QueryFilter[]{}));
            if (Objects.isNull(count) || count == 0){
                useroOrderPageDto.setCount(0);
                return useroOrderPageDto;
            }
            useroOrderPageDto.setCount(count);
            //获取订单信息
            int offset = (orderListReq.getPageIndex() - 1) * orderListReq.getPageSize();
            orderListReq.setOffset(offset);
            orderListReq.setIdlist(siteids);
            List<OrderDto> orders = waterOrderDao.getOrgOrderListForSite(orderListReq);
            useroOrderPageDto.setList(orders);
        }else if(PlatformStatusEnum.isPLANTFORM_ELM(orderListReq.getPlatform())){

        }else if(PlatformStatusEnum.isPLANTFORM_MT(orderListReq.getPlatform())){

        }else if(PlatformStatusEnum.isPLANTFORM_SJZJ(orderListReq.getPlatform())){

        }else {
            throw new PublicException("暂不支持该平台");
        }
        return useroOrderPageDto;
    }

    private UseroOrderPageDto getShopOrderList(OrderListReq orderListReq, UseroOrderPageDto useroOrderPageDto) {
        //获取门店基本信息
        List<UserShopDto> userShopList = sysShopUserDao.getUserShopList(orderListReq.getUserId());
        if (CollectionUtils.isEmpty(userShopList)){
            throw new PublicException("用户角色异常,该用户非门店");
        }
        if (Objects.nonNull(orderListReq.getPlatform())){
            userShopList = userShopList.stream().filter(v -> Objects.equals(v.getPlatform(), orderListReq.getPlatform())).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(userShopList)){
            throw new PublicException("该用户权限不足");
        }
        List<Integer> shopids = userShopList.stream().map(UserShopDto::getShopId).collect(Collectors.toList());
        //根据商户code和平台类型查询订单
        if (PlatformStatusEnum.isPLANTFORM_JD(orderListReq.getPlatform())){
            List<QueryFilter> qfs = new ArrayList<>();
            qfs.add(new QueryFilter("deliveryStationNoIsv", PMLO.IN, shopids));
            if (Objects.nonNull(orderListReq.getStatus())){
                qfs.add(new QueryFilter("orderStatus", orderListReq.getStatus()));
            }
            Integer count = waterOrderDao.findCount(OrderDto.class, qfs.toArray(new QueryFilter[]{}));
            if (Objects.isNull(count) || count == 0){
                useroOrderPageDto.setCount(0);
                return useroOrderPageDto;
            }
            useroOrderPageDto.setCount(count);
            //获取订单信息
            int offset = (orderListReq.getPageIndex() - 1) * orderListReq.getPageSize();
            orderListReq.setOffset(offset);
            orderListReq.setIdlist(shopids);
            List<OrderDto> orders = waterOrderDao.getOrgOrderList(orderListReq);
            useroOrderPageDto.setList(orders);
        }else if(PlatformStatusEnum.isPLANTFORM_ELM(orderListReq.getPlatform())){

        }else if(PlatformStatusEnum.isPLANTFORM_MT(orderListReq.getPlatform())){

        }else if(PlatformStatusEnum.isPLANTFORM_SJZJ(orderListReq.getPlatform())){

        }else {
            throw new PublicException("暂不支持该平台");
        }
        return useroOrderPageDto;
    }

    /**
     * 获取商户订单
     * @param orderListReq
     * @param useroOrderPageDto
     * @return
     */
    private UseroOrderPageDto getOrgOrderList(OrderListReq orderListReq, UseroOrderPageDto useroOrderPageDto) {
        //获取商家基本信息
        List<SysOrgPojo> sysOrgPoList = sysOrgUserDao.getOrgBaseInfo(orderListReq.getUserId());
        if (CollectionUtils.isEmpty(sysOrgPoList)){
            throw new PublicException("用户角色异常,该用户非商户");
        }
        if (Objects.nonNull(orderListReq.getPlatform())){
            sysOrgPoList = sysOrgPoList.stream().filter(v -> Objects.equals(v.getPlatform(), orderListReq.getPlatform())).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(sysOrgPoList)){
            throw new PublicException("该用户权限不足");
        }
        List<Integer> orgids = sysOrgPoList.stream().map(SysOrgPojo::getOrgId).collect(Collectors.toList());
        //根据商户code和平台类型查询订单
        if (PlatformStatusEnum.isPLANTFORM_JD(orderListReq.getPlatform())){
            List<QueryFilter> qfs = new ArrayList<>();
            qfs.add(new QueryFilter("org_id", PMLO.IN, orgids));
            if (Objects.nonNull(orderListReq.getStatus())){
                qfs.add(new QueryFilter("orderStatus", orderListReq.getStatus()));
            }
            Integer count = waterOrderDao.findCount(OrderDto.class, qfs.toArray(new QueryFilter[]{}));
            if (Objects.isNull(count) || count == 0){
                useroOrderPageDto.setCount(0);
                return useroOrderPageDto;
            }
            useroOrderPageDto.setCount(count);
            //获取订单信息
            int offset = (orderListReq.getPageIndex() - 1) * orderListReq.getPageSize();
            orderListReq.setOffset(offset);
            orderListReq.setIdlist(orgids);
            List<OrderDto> orders = waterOrderDao.getOrgOrderList(orderListReq);
            useroOrderPageDto.setList(orders);
        }else if(PlatformStatusEnum.isPLANTFORM_ELM(orderListReq.getPlatform())){

        }else if(PlatformStatusEnum.isPLANTFORM_MT(orderListReq.getPlatform())){

        }else if(PlatformStatusEnum.isPLANTFORM_SJZJ(orderListReq.getPlatform())){

        }else {
            throw new PublicException("暂不支持该平台");
        }
        return useroOrderPageDto;
    }

    @Override
    public List<UserShopDto> getUserShopList(Integer userId) {
        if (Objects.isNull(userId)){
            throw new PublicException("参数不全");
        }
        return sysShopUserDao.getUserShopList(userId);
    }
}
