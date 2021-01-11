package com.wx.watersupplierservice.service;

import com.wx.watersupplierservice.dto.WatersDto;
import com.wx.watersupplierservice.req.SendWatersReq;

import java.util.List;

/**
 * @author : weili
 * @version V1.0
 * @Project: watersupplierservice
 * @Package com.wx.watersupplierservice.service
 * @Description: TODO
 * @date Date : 2021年01月11日 23:05
 */
public interface BusinessService {

    List<WatersDto> getSendWaterList(SendWatersReq sendWatersReq);
}
