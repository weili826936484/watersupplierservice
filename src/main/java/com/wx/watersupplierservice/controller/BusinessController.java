package com.wx.watersupplierservice.controller;/**
 * @Project: watersupplierservice
 * @Package com.wx.watersupplierservice.controller
 * @Description: TODO
 * @author : weili
 * @date Date : 2021年01月11日 21:19
 * @version V1.0
 */

import com.wx.watersupplierservice.dto.WatersDto;
import com.wx.watersupplierservice.req.SendWatersReq;
import com.wx.watersupplierservice.resp.ObjectResults;
import com.wx.watersupplierservice.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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

    @PostMapping("/getSendWaterList")
    public ObjectResults<List<WatersDto>> getSendWaterList(SendWatersReq sendWatersReq){
        return ObjectResults.createSuccessResult(businessService.getSendWaterList(sendWatersReq));
    }



}
