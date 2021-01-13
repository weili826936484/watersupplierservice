package com.wx.watersupplierservice.controller;

import com.wx.watersupplierservice.dto.DistributeSuitDto;
import com.wx.watersupplierservice.dto.OrderMonthDto;
import com.wx.watersupplierservice.dto.StatusDto;
import com.wx.watersupplierservice.req.OrderMonthReq;
import com.wx.watersupplierservice.resp.ObjectResults;
import com.wx.watersupplierservice.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: StatisticsController
 *
 * @description:
 *
 * @author: weili
 *
 * @create: 2021-01-12 00:08
 **/
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/getStatuslist/{type}")
    public ObjectResults<List<StatusDto>> getStatuslist(@PathVariable Integer type){
        return ObjectResults.createSuccessResult(statisticsService.getStatuslist(type));
    }

    @PostMapping("/org/getOrderMonthList")
    public ObjectResults<List<OrderMonthDto>> getOrderMonthList(OrderMonthReq orderMonthReq){
        //return ObjectResults.createSuccessResult(statisticsService.getSendWaterList(orderMonthReq));
        return null;
    }

    @PostMapping("/org/getDistributeSuitList")
    public ObjectResults<List<DistributeSuitDto>> getDistributeSuitList(OrderMonthReq orderMonthReq){
        //return ObjectResults.createSuccessResult(statisticsService.getDistributeSuitList(orderMonthReq));
        return null;
    }
}
