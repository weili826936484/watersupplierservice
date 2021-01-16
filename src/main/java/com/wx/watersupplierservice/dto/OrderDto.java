package com.wx.watersupplierservice.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Integer id;
    private String platform;
    private String orderid;
    private String orderstatus;
    private Date orderstarttime;
    private Date orderprestartdeliverytime;
    private Date orderpreenddeliverytime;
    private String orderbuyerremark;
    private Date pickdeadline;
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

    private Integer orderBusinessId;
    private String optCode;
    private String optCodeName;


    private List<ProductDto> list;
    @Data
    public static class ProductDto{
        private Integer productId;
        private String skuName;
        private String skuCount;
    }
}
