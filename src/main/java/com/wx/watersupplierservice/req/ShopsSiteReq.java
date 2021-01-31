package com.wx.watersupplierservice.req;

import lombok.Data;

import java.util.List;

@Data
public class ShopsSiteReq {
    public Integer siteId;
    private Integer userId;
    private String siteName;
    private String siteStatus;
    private List<Integer> shops;
}
