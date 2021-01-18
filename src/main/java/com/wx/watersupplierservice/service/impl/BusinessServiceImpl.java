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
import lombok.Data;
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

    @Autowired
    private OrderBusinessProcessDao orderBusinessProcessDao;

    @Autowired
    private SysCustomerDao sysCustomerDao;
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
        if (Objects.isNull(changeOrder) ||Objects.isNull(changeOrder.getOptCode())){
            throw new PublicException("参数不全！");
        }
        String orderStatus;
        String preOrderStatus;
        if (OPTStatusEnum.isSITE_CANCEL(changeOrder.getOptCode())){
            orderStatus = OrderStatusEnum.ORDER_OUT.getCode();
            preOrderStatus = OrderStatusEnum.ORDER_CANCEL_REQ.getCode();
            cancelOrder(changeOrder,orderStatus,preOrderStatus);
        } else if (OPTStatusEnum.isSITE_FENDAN(changeOrder.getOptCode())){
            orderStatus = OrderStatusEnum.ORDER_OUT.getCode();
            preOrderStatus = OrderStatusEnum.ORDER_SEND.getCode();
            splitOrder(changeOrder,orderStatus,preOrderStatus);
        } else if (OPTStatusEnum.isSITE_JIEDAN(changeOrder.getOptCode())){
            orderStatus = OrderStatusEnum.ORDER_SEND.getCode();
            preOrderStatus = OrderStatusEnum.ORDER_SEND.getCode();
            suitReceiveOrder(changeOrder,orderStatus,preOrderStatus);
        } else if (OPTStatusEnum.isSITE_OK(changeOrder.getOptCode())){
            orderStatus = OrderStatusEnum.ORDER_SEND.getCode();
            preOrderStatus = OrderStatusEnum.ORDER_RECEIVE.getCode();
            finishOrder(changeOrder,orderStatus,preOrderStatus);
        } else if (OPTStatusEnum.isSITE_REFUSE(changeOrder.getOptCode())){
            orderStatus = OrderStatusEnum.ORDER_SEND.getCode();
            preOrderStatus = OrderStatusEnum.ORDER_OUT.getCode();
            refuseOrder(changeOrder,orderStatus,preOrderStatus);
        } else {
            throw new PublicException("不支持当前操作！");
        }
    }

    private void refuseOrder(ChangeOrderReq changeOrder, String orderStatus, String preOrderStatus) {
        if (Objects.isNull(changeOrder.getOrderId())){
            throw new PublicException("参数不全");
        }
        WaterOrderPo waterOrderPo = waterOrderDao.findById(WaterOrderPo.class, changeOrder.getOrderId());
        if (Objects.isNull(waterOrderPo) || !orderStatus.equals(waterOrderPo.getOrderstatus())){
            throw new PublicException("订单状态有变化，请重新选择！");
        }
        OrderBusinessPo orderBusinessPo = orderBusinessDao.findByOrderId(changeOrder.getOrderId());
        if (Objects.isNull(orderBusinessPo)){
            throw new PublicException("参数错误");
        }
        //如操作明细表
        OrderBusinessProcessPo orderBusinessProcessPo = new OrderBusinessProcessPo();
        orderBusinessProcessPo.setBusinessId(orderBusinessPo.getId());
        orderBusinessProcessPo.setCreateBy(changeOrder.getUserId());
        orderBusinessProcessPo.setOptCode(changeOrder.getOptCode());
        orderBusinessProcessPo.setUpdateTime(new Date());
        orderBusinessProcessPo.setResultInfo(changeOrder.getRemark());
        int num = orderBusinessProcessDao.insert(orderBusinessProcessPo);
        if (num == 0){
            throw new PublicException("操作失败！");
        }
        orderBusinessPo.setUpdateBy(changeOrder.getUserId());
        orderBusinessPo.setUpdateTime(new Date());
        orderBusinessPo.setOptCode(changeOrder.getOptCode());
        orderBusinessDao.update(orderBusinessPo);
        int index = waterOrderDao.updateStatusById(waterOrderPo.getId(),preOrderStatus,waterOrderPo.getVersion(),changeOrder.getUserId());
        if (index == 0 ){
            throw new PublicException("选中订单状态有变化，请重新选择");
        }
        //todo 向商户推送

    }

    private void finishOrder(ChangeOrderReq changeOrder, String orderStatus, String preOrderStatus) {
        if (Objects.isNull(changeOrder.getOrderId())){
            throw new PublicException("参数不全");
        }
        WaterOrderPo waterOrderPo = waterOrderDao.findById(WaterOrderPo.class, changeOrder.getOrderId());
        if (Objects.isNull(waterOrderPo) || !orderStatus.equals(waterOrderPo.getOrderstatus())){
            throw new PublicException("订单状态有变化，请重新选择！");
        }
        OrderBusinessPo orderBusinessPo = orderBusinessDao.findByOrderId(changeOrder.getOrderId());
        if (Objects.isNull(orderBusinessPo)){
            throw new PublicException("参数错误");
        }
        //如操作明细表
        OrderBusinessProcessPo orderBusinessProcessPo = new OrderBusinessProcessPo();
        orderBusinessProcessPo.setBusinessId(orderBusinessPo.getId());
        orderBusinessProcessPo.setCreateBy(changeOrder.getUserId());
        orderBusinessProcessPo.setOptCode(changeOrder.getOptCode());
        orderBusinessProcessPo.setUpdateTime(new Date());
        orderBusinessProcessPo.setResultInfo(changeOrder.getRemark());
        int num = orderBusinessProcessDao.insert(orderBusinessProcessPo);
        if (num == 0){
            throw new PublicException("操作失败！");
        }
        orderBusinessPo.setUpdateBy(changeOrder.getUserId());
        orderBusinessPo.setUpdateTime(new Date());
        orderBusinessPo.setOptCode(changeOrder.getOptCode());
        orderBusinessDao.update(orderBusinessPo);
        //对人员进行检测，并将次数+1操作
        String platform = waterOrderPo.getPlatform();
        String buyerpin = waterOrderPo.getBuyerpin();
        SysCustomerPo sysCustomerPo = sysCustomerDao.checkExists(platform,buyerpin);
        if (Objects.isNull(sysCustomerPo)){
            sysCustomerPo = new SysCustomerPo();
            sysCustomerPo.setPlatformSource(platform);
            sysCustomerPo.setPlatformUserid(buyerpin);
            sysCustomerPo.setConsumeCount(1);
            sysCustomerPo.setConsumeMoney(Long.parseLong(waterOrderPo.getOrderbuyerpayablemoney()));
            sysCustomerPo.setCustomerName(waterOrderPo.getBuyerfullname());
            sysCustomerDao.insert(sysCustomerPo);
        } else {
            sysCustomerPo.setConsumeCount(sysCustomerPo.getConsumeCount()+1);
            sysCustomerPo.setConsumeMoney(sysCustomerPo.getConsumeMoney()+Long.parseLong(waterOrderPo.getOrderbuyerpayablemoney()));
            sysCustomerDao.update(sysCustomerPo);
        }
        //for循环更新order表
        int index = waterOrderDao.updateStatusById(waterOrderPo.getId(),preOrderStatus,waterOrderPo.getVersion(),changeOrder.getUserId());
        if (index == 0 ){
            throw new PublicException("选中订单状态有变化，请重新选择");
        }

        //todo 向京东推送



        //todo 向商户推送

    }

    private void suitReceiveOrder(ChangeOrderReq changeOrder, String orderStatus, String preOrderStatus) {
        if (Objects.isNull(changeOrder.getOrderId())){
            throw new PublicException("参数不全");
        }
        WaterOrderPo waterOrderPo = waterOrderDao.findById(WaterOrderPo.class, changeOrder.getOrderId());
        if (Objects.isNull(waterOrderPo) || !orderStatus.equals(waterOrderPo.getOrderstatus())){
            throw new PublicException("订单状态有变化，请重新选择！");
        }
        OrderBusinessPo orderBusinessPo = orderBusinessDao.findByOrderId(changeOrder.getOrderId());
        if (Objects.isNull(orderBusinessPo)){
            throw new PublicException("参数错误");
        }
        //如操作明细表
        OrderBusinessProcessPo orderBusinessProcessPo = new OrderBusinessProcessPo();
        orderBusinessProcessPo.setBusinessId(orderBusinessPo.getId());
        orderBusinessProcessPo.setCreateBy(changeOrder.getUserId());
        orderBusinessProcessPo.setOptCode(changeOrder.getOptCode());
        orderBusinessProcessPo.setUpdateTime(new Date());
        orderBusinessProcessPo.setResultInfo(changeOrder.getRemark());
        int num = orderBusinessProcessDao.insert(orderBusinessProcessPo);
        if (num == 0){
            throw new PublicException("操作失败！");
        }
        orderBusinessPo.setUpdateBy(changeOrder.getUserId());
        orderBusinessPo.setUpdateTime(new Date());
        orderBusinessPo.setOptCode(changeOrder.getOptCode());
        orderBusinessDao.update(orderBusinessPo);
        //for循环更新order表
        int index = waterOrderDao.updateStatusById(waterOrderPo.getId(),preOrderStatus,waterOrderPo.getVersion(),changeOrder.getUserId());
        if (index == 0 ){
            throw new PublicException("选中订单状态有变化，请重新选择");
        }
        //todo 向商户推送？？？？
    }

    private void splitOrder(ChangeOrderReq changeOrder, String orderStatus, String preOrderStatus) {
        List<ChangeOrderReq.OrderSite> orderSiteList = changeOrder.getOrderSiteList();
        List<Integer> orders = orderSiteList.stream().map(ChangeOrderReq.OrderSite::getOrderId).collect(Collectors.toList());
        List<QueryFilter> qfs = new ArrayList<>();
        qfs.add(new QueryFilter("id", PMLO.IN, orders));
        List<WaterOrderPo> waterOrderPos = waterOrderDao.find(WaterOrderPo.class, qfs.toArray(new QueryFilter[]{}));

        long count = waterOrderPos.stream().filter(e -> !orderStatus.equals(e.getOrderstatus())).count();
        if (count > 0L){
            throw new PublicException("订单状态有变化，请重新选择！");
        }
        Map<Integer, Integer> orderSiteMap = orderSiteList.stream().collect(Collectors.toMap(ChangeOrderReq.OrderSite::getOrderId, ChangeOrderReq.OrderSite::getSiteId, (a, b) -> b));
        List<OrderBusinessPo> orderBusinessPos = new ArrayList<>();
        //批量入business
        for (WaterOrderPo po : waterOrderPos){
            if (!orderSiteMap.containsKey(po.getId())){
                continue;
            }
            OrderBusinessPo orderBusinessPo = new OrderBusinessPo();
            orderBusinessPo.setOptCode(changeOrder.getOptCode());
            orderBusinessPo.setOrderId(po.getId());
            orderBusinessPo.setPlatform(po.getPlatform());
            orderBusinessPo.setCreateBy(changeOrder.getUserId());
            orderBusinessPo.setSiteId(orderSiteMap.get(po.getId()));
            orderBusinessPos.add(orderBusinessPo);
        }
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
            orderBusinessProcessPo.setOptCode(changeOrder.getOptCode());
            orderBusinessProcessPo.setUpdateTime(new Date());
            orderBusinessProcessPo.setResultInfo(changeOrder.getRemark());
            orderBusinessProcessPos.add(orderBusinessProcessPo);
        });
        num = orderBusinessProcessDao.insertList(orderBusinessProcessPos);
        if (num == 0){
            throw new PublicException("操作失败！");
        }
        //for循环更新order表
        for (WaterOrderPo waterOrderPo : waterOrderPos){
            int index = waterOrderDao.updateStatusById(waterOrderPo.getId(),preOrderStatus,waterOrderPo.getVersion(),changeOrder.getUserId());
            if (index == 0 ){
                throw new PublicException("选中订单状态有变化，请重新选择");
            }
        }
        //todo 向京东推送配送中信息

        //todo 推送order_business信息
        for(OrderBusinessPo orderBusinessPo : orderBusinessPos){

        }

    }

    private void cancelOrder(ChangeOrderReq changeOrder, String orderStatus, String preOrderStatus) {
        if (Objects.isNull(changeOrder.getOrderId())){
            throw new PublicException("参数不全");
        }
        WaterOrderPo waterOrderPo = waterOrderDao.findById(WaterOrderPo.class, changeOrder.getOrderId());
        if (Objects.isNull(waterOrderPo) || !orderStatus.equals(waterOrderPo.getOrderstatus())){
            throw new PublicException("订单状态有变化，请重新选择！");
        }
        OrderBusinessPo orderBusinessPo = orderBusinessDao.findByOrderId(changeOrder.getOrderId());
        if (Objects.isNull(orderBusinessPo)){
            throw new PublicException("参数错误");
        }
        //如操作明细表
        OrderBusinessProcessPo orderBusinessProcessPo = new OrderBusinessProcessPo();
        orderBusinessProcessPo.setBusinessId(orderBusinessPo.getId());
        orderBusinessProcessPo.setCreateBy(changeOrder.getUserId());
        orderBusinessProcessPo.setOptCode(changeOrder.getOptCode());
        orderBusinessProcessPo.setUpdateTime(new Date());
        orderBusinessProcessPo.setResultInfo(changeOrder.getRemark());
        int num = orderBusinessProcessDao.insert(orderBusinessProcessPo);
        if (num == 0){
            throw new PublicException("操作失败！");
        }
        orderBusinessPo.setUpdateBy(changeOrder.getUserId());
        orderBusinessPo.setUpdateTime(new Date());
        orderBusinessPo.setOptCode(changeOrder.getOptCode());
        orderBusinessDao.update(orderBusinessPo);
        //for循环更新order表
        int index = waterOrderDao.updateStatusById(waterOrderPo.getId(),preOrderStatus,waterOrderPo.getVersion(),changeOrder.getUserId());
        if (index == 0 ){
            throw new PublicException("选中订单状态有变化，请重新选择");
        }
        //todo 向京东推送

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
