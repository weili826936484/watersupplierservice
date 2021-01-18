package com.wx.watersupplierservice.req;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ChangeOrderReq {
    /**
     * key为orderid value为水站id 分单才传
     */
    private List<OrderSite> orderSiteList;
    private String optCode;
    private String remark;
    private Integer userId;
    /**
     * 对单个订单的操作
     */
    private Integer orderId;

    @Data
    public static class OrderSite{
        private Integer orderId;
        private Integer siteId;
    }
}
