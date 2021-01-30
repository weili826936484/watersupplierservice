package com.wx.watersupplierservice.dto;

import lombok.Data;

@Data
public class UserShopDto {
    public Integer shopId;

    private String shopName;

    private String platform;

    private String shopCode;

    private String shopAddress;

    private String shopLeader;

    private String shopTel;

    private String shopStatus;

    private Integer sysShopUserId;
}
