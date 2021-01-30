package com.wx.watersupplierservice.dto;

import lombok.Data;

@Data
public class UserShopSiteDto {
    public Integer siteId;

    private String siteName;

    private String siteAddress;

    private String siteLeader;

    private String siteTel;

    private String siteStatus;

    private String userId;
}
