package com.wx.watersupplierservice.controller;

import com.wx.watersupplierservice.dto.UserShopDto;
import com.wx.watersupplierservice.dto.UseroOrderPageDto;
import com.wx.watersupplierservice.dto.WatersPageDto;
import com.wx.watersupplierservice.req.OrderListReq;
import com.wx.watersupplierservice.req.SendWatersReq;
import com.wx.watersupplierservice.resp.ObjectResults;
import com.wx.watersupplierservice.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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


    @Autowired
    private BusinessService businessService;

    private static final Executor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000), (r, executor) -> System.out.println("订单推送失败！"));
    @PostMapping("/getSendWaterList")
    public ObjectResults<WatersPageDto> getSendWaterList(SendWatersReq sendWatersReq){
        return ObjectResults.createSuccessResult(businessService.getSendWaterList(sendWatersReq));
    }


    /**
     * 获取用户关联的门店
     * @param userId
     * @return
     */
    @GetMapping("/getShopList/{userId}")
    public ObjectResults<List<UserShopDto>> getUserShopList(@PathVariable Integer userId){
        return ObjectResults.createSuccessResult(businessService.getUserShopList(userId));
    }

    /**
     * 获取订单信息
     * @return
     */
    @PostMapping("/getOrderList")
    public ObjectResults<UseroOrderPageDto> getOrderList(OrderListReq getOrderListReq){
        return ObjectResults.createSuccessResult(businessService.getOrderList(getOrderListReq));
    }

}
