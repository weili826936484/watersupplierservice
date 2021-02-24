package com.wx.watersupplierservice.controller;

import com.wx.watersupplierservice.dto.UserShopDto;
import com.wx.watersupplierservice.dto.UserShopSites;
import com.wx.watersupplierservice.dto.UseroOrderPageDto;
import com.wx.watersupplierservice.dto.WatersPageDto;
import com.wx.watersupplierservice.po.SysShopPo;
import com.wx.watersupplierservice.req.*;
import com.wx.watersupplierservice.resp.ObjectResults;
import com.wx.watersupplierservice.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: BusinessController
 *
 * @description: 流程控制
 *
 * @author: weili
 *
 * @create: 2021-01-11 21:19
 **/
@RestController
@RequestMapping("/api/business")
public class BusinessController {

    private final Logger logger= LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private BusinessService businessService;

    private static final Executor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000), (r, executor) -> System.out.println("订单推送失败！"));

    @PostMapping("/getSendWaterList")
    public ObjectResults<WatersPageDto> getSendWaterList(@RequestBody SendWatersReq sendWatersReq){
        return ObjectResults.createSuccessResult(businessService.getSendWaterList(sendWatersReq));
    }


    /**
     * 获取用户关联的门店
     * @param shopsReq
     * @return
     */
    @PostMapping("/getShopList")
    public ObjectResults<List<UserShopDto>> getUserShopList(@RequestBody ShopsReq shopsReq){
        return ObjectResults.createSuccessResult(businessService.getUserShopList(shopsReq));
    }

    @PostMapping("updateUserShop")
    public ObjectResults updateUserShop(@RequestBody SysShopPo sysShopPo){
        businessService.updateUserShop(sysShopPo);
        return ObjectResults.createSuccessResult();
    }

    @PostMapping("deleteUserShop/{sysShopUserId}")
    public ObjectResults deleteUserShop(@PathVariable Integer sysShopUserId){
        businessService.deleteUserShop(sysShopUserId);
        return ObjectResults.createSuccessResult();
    }

    @PostMapping("/getShopSiteList")
    public ObjectResults<UserShopSites> getShopSiteList(@RequestBody ShopsSiteReq shopsSiteReq){
        List<UserShopSites.UserShopSiteDto> shopSiteList = businessService.getShopSiteList(shopsSiteReq);
        UserShopSites userShopSites = new UserShopSites();
        userShopSites.setList(shopSiteList);
        return ObjectResults.createSuccessResult(userShopSites);
    }

    @PostMapping("updateShopSite")
    public ObjectResults updateShopSite(@RequestBody UserShopSites.UserShopSiteDto userShopSiteDto){
        businessService.updateShopSite(userShopSiteDto);
        return ObjectResults.createSuccessResult();
    }

    /**
     * 获取订单信息
     * @return
     */
    @PostMapping("/getOrderList")
    public ObjectResults<UseroOrderPageDto> getOrderList(@RequestBody OrderListReq orderListReq){
        return ObjectResults.createSuccessResult(businessService.getOrderList(orderListReq));
    }

    /**
     * 改变订单状态
     * @param changeOrder
     * @return
     */
    @PostMapping("/changeOrder")
    public ObjectResults changeOrder(@RequestBody ChangeOrderReq changeOrder) throws UnsupportedEncodingException {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("----------------------请求时间：{},参数：{}",sdf.format(now),changeOrder.toString());
        businessService.changeOrder(changeOrder);
        return ObjectResults.createSuccessResult();
    }

}
