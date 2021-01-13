package com.wx.watersupplierservice.service.impl;

import com.wx.watersupplierservice.dto.StatusDto;
import com.wx.watersupplierservice.enums.SiteStatusEnum;
import com.wx.watersupplierservice.enums.OrderStatusEnum;
import com.wx.watersupplierservice.enums.PlatformStatusEnum;
import com.wx.watersupplierservice.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: StatisticsServiceImpl
 *
 * @description:
 *
 * @author: weili
 *
 * @create: 2021-01-12 00:12
 **/
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public List<StatusDto> getStatuslist(Integer type) {
        if ("platform".equals(type)){
            return PlatformStatusEnum.getPlatformStatus();
        } else if("order_status".equals(type)){
            return OrderStatusEnum.getOrderStatus();
        } else {
            return SiteStatusEnum.getSiteStatus();
        }
    }
}
