package com.wx.watersupplierservice.service;

import com.wx.watersupplierservice.dto.StatusDto;

import java.util.List;

/**
 * @author : weili
 * @version V1.0
 * @Project: watersupplierservice
 * @Package com.wx.watersupplierservice.service
 * @Description: TODO
 * @date Date : 2021年01月12日 0:11
 */
public interface StatisticsService {

    List<StatusDto> getStatuslist(Integer type);
}
