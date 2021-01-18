package com.wx.watersupplierservice.service.impl;

import com.wx.watersupplierservice.dao.*;
import com.wx.watersupplierservice.dto.OrderDto;
import com.wx.watersupplierservice.dto.UserShopDto;
import com.wx.watersupplierservice.dto.UseroOrderPageDto;
import com.wx.watersupplierservice.dto.WatersPageDto;
import com.wx.watersupplierservice.enums.OPTStatusEnum;
import com.wx.watersupplierservice.enums.OrderStatusEnum;
import com.wx.watersupplierservice.enums.PlatformStatusEnum;
import com.wx.watersupplierservice.enums.UserRoleEnum;
import com.wx.watersupplierservice.exception.PublicException;
import com.wx.watersupplierservice.po.*;
import com.wx.watersupplierservice.pojo.SysOrgPojo;
import com.wx.watersupplierservice.pojo.SysSitePojo;
import com.wx.watersupplierservice.req.ChangeOrderReq;
import com.wx.watersupplierservice.req.OrderListReq;
import com.wx.watersupplierservice.req.SendWatersReq;
import com.wx.watersupplierservice.service.BusinessService;
import com.xdf.pscommon.mybatis.rt.PMLO;
import com.xdf.pscommon.mybatis.rt.QueryFilter;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.*;
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

    @Autowired
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

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void changeOrder(ChangeOrderReq changeOrder) {
        if (Objects.isNull(changeOrder) || CollectionUtils.isEmpty(changeOrder.getOrderSiteIds())
                ||Objects.isNull(changeOrder.getOptCode())){
            throw new PublicException("参数不全！");
        }
        if (OPTStatusEnum.isSITE_CANCEL(changeOrder.getOptCode())){
            cancelOrder(changeOrder);
        } else if (OPTStatusEnum.isSITE_FENDAN(changeOrder.getOptCode())){
            splitOrder(changeOrder);
        } else if (OPTStatusEnum.isSITE_JIEDAN(changeOrder.getOptCode())){
            suitReceiveOrder(changeOrder);
        } else if (OPTStatusEnum.isSITE_OK(changeOrder.getOptCode())){
            finishOrder(changeOrder);
        } else if (OPTStatusEnum.isSITE_REFUSE(changeOrder.getOptCode())){
            refuseOrder(changeOrder);
        } else {

        }
    }

    private void refuseOrder(ChangeOrderReq changeOrder) {

    }

    private void finishOrder(ChangeOrderReq changeOrder) {
    }

    private void suitReceiveOrder(ChangeOrderReq changeOrder) {
    }

    private void splitOrder(ChangeOrderReq changeOrder) {
        List<Map<Integer, Integer>> orderSiteIds = changeOrder.getOrderSiteIds();
        HashSet<Integer> sets = new HashSet<>();
        orderSiteIds.forEach(e-> sets.addAll(e.keySet()));
        List<Integer> orders = new ArrayList(sets);
        List<QueryFilter> qfs = new ArrayList<>();
        qfs.add(new QueryFilter("id", PMLO.IN, orders));
        List<WaterOrderPo> waterOrderPos = waterOrderDao.find(WaterOrderPo.class, qfs.toArray(new QueryFilter[]{}));
        long count = waterOrderPos.stream().filter(e -> !OrderStatusEnum.isORDER_OUT(e.getOrderstatus())).count();
        if (count > 0L){
            throw new PublicException("订单状态有变化，请重新选择！");
        }
        List<OrderBusinessPo> orderBusinessPos = new ArrayList<>();
        //批量入business
        waterOrderPos.forEach(po->{
            OrderBusinessPo orderBusinessPo = new OrderBusinessPo();
            orderBusinessPo.setOptCode(OPTStatusEnum.SITE_FENDAN.getCode());
            orderBusinessPo.setOrderId(po.getId());
            orderBusinessPo.setPlatform(po.getPlatform());
            orderBusinessPo.setCreateBy(changeOrder.getUserId());
            orderBusinessPo.setSiteId(changeOrder.getSiteId());
            orderBusinessPos.add(orderBusinessPo);
        });
        int num = orderBusinessDao.insertList(orderBusinessPos);
        if (num == 0){
            throw new PublicException("操作失败！");
        }
        //如操作明细表
        List<OrderBusinessProcessPo> orderBusinessProcessPos = new ArrayList<>();
        orderBusinessPos.forEach(e->{
            OrderBusinessProcessPo orderBusinessProcessPo = new OrderBusinessProcessPo();
            orderBusinessProcessPo.setBusinessId(e.getId());
            orderBusinessProcessPo.setCreateBy(changeOrder.getUserId());

        });
    }

    private void cancelOrder(ChangeOrderReq changeOrder) {

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
//        if (Objects.nonNull(orderListReq.getPlatform())){
//            userShopSiteList = userShopSiteList.stream().filter(v -> Objects.equals(v.getPlatform(), orderListReq.getPlatform())).collect(Collectors.toList());
//        }
//        if (CollectionUtils.isEmpty(userShopSiteList)){
//            throw new PublicException("该用户权限不足");
//        }
        List<Integer> siteids = userShopSiteList.stream().map(SysSitePojo::getSiteId).collect(Collectors.toList());
        //根据商户code和平台类型查询订单
        if (PlatformStatusEnum.isPLANTFORM_JD(orderListReq.getPlatform())){
            List<QueryFilter> qfs = new ArrayList<>();
            if (Objects.nonNull(orderListReq.getOrderBusinessId())){
                qfs.add(new QueryFilter("id", orderListReq.getOrderBusinessId()));
            } else {
                qfs.add(new QueryFilter("site_id", PMLO.IN, siteids));
                if (Objects.nonNull(orderListReq.getStatus())){
                    qfs.add(new QueryFilter("opt_code", orderListReq.getStatus()));
                }
            }
            QueryFilter[] queryFilters = qfs.toArray(new QueryFilter[]{});
            Integer count = orderBusinessDao.findCount(OrderBusinessPo.class, queryFilters);
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
            orders.forEach(e->e.setPlatformName(PlatformStatusEnum.getName(e.getPlatform())));
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
        List<String> shopids = userShopList.stream().map(UserShopDto::getShopCode).collect(Collectors.toList());
        //根据商户code和平台类型查询订单
        if (PlatformStatusEnum.isPLANTFORM_JD(orderListReq.getPlatform())){
            List<QueryFilter> qfs = new ArrayList<>();
            if (Objects.nonNull(orderListReq.getOrderId())){
                qfs.add(new QueryFilter("id", orderListReq.getOrderId()));
            }else {
                qfs.add(new QueryFilter("deliveryStationNoIsv", PMLO.IN, shopids));
                if (Objects.nonNull(orderListReq.getStatus())){
                    qfs.add(new QueryFilter("orderStatus", orderListReq.getStatus()));
                }
            }
            Integer count = waterOrderDao.findCount(WaterOrderPo.class, qfs.toArray(new QueryFilter[]{}));
            if (Objects.isNull(count) || count == 0){
                useroOrderPageDto.setCount(0);
                return useroOrderPageDto;
            }
            useroOrderPageDto.setCount(count);
            //获取订单信息
            int offset = (orderListReq.getPageIndex() - 1) * orderListReq.getPageSize();
            orderListReq.setOffset(offset);
            orderListReq.setShoplist(shopids);
            List<OrderDto> orders = waterOrderDao.getOrgOrderList(orderListReq);
            orders.forEach(e->e.setPlatformName(PlatformStatusEnum.getName(e.getPlatform())));
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
            if (Objects.nonNull(orderListReq.getOrderId())){
                qfs.add(new QueryFilter("id", orderListReq.getOrderId()));
            }else {
                qfs.add(new QueryFilter("org_id", PMLO.IN, orgids));
                if (Objects.nonNull(orderListReq.getStatus())){
                    qfs.add(new QueryFilter("orderStatus", orderListReq.getStatus()));
                }
            }
            Integer count = waterOrderDao.findCount(WaterOrderPo.class, qfs.toArray(new QueryFilter[]{}));
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
            orders.forEach(e->e.setPlatformName(PlatformStatusEnum.getName(e.getPlatform())));
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
