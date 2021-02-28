package com.wx.watersupplierservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wx.watersupplierservice.req.ChangeOrderReq;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Integer id;
    private String platform;
    private String platformName;
    private String orderid;
    private String orderstatus;
    private String orderStatusName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderstarttime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderprestartdeliverytime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderpreenddeliverytime;
    private long lessTime;
    private String orderbuyerremark;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date pickdeadline;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date ordercanceltime;
    private String ordercancelremark;
    private String buyerpin;
    private String buyerfullname;
    private String buyerfulladdress;
    private String buyertelephone;
    private String buyermobile;
    private String deliverystationno;
    private String deliverystationnoisv;
    private String deliverystationname;
    private String orderpaytype;
    private String ordertotalmoney;
    private String ordergoodsmoney;
    private String orderbuyerpayablemoney;
    private String orderNum;
    private Integer orderBusinessId;
    private String optCode;
    private String optCodeName;

    private Integer siteId;
    private String siteName;
    private String siteTel;
    private int times;
    private Integer remand;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String deliveryConfirmTime;
    /**
     * 1表示可以批量分单
     * -1表示不可以
     */
    private Integer batchSplitStatus;

    private List<ProductDto> productList;

    private List<OrderSiteBefor> orderSiteBeforList;

    @Data
    public static class ProductDto{
        private Integer productId;
        private String skuName;
        private String skuCount;
        private String skuJdPrice;
    }

    @Data
    public static class OrderSiteBefor{
        private Integer orderId;
        private Integer siteId;
        private String siteName;
        private String address;
    }
}
