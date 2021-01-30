package com.wx.watersupplierservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserShopSites{

    private List<UserShopSiteDto> list;

    @Data
    public static class UserShopSiteDto {
        public Integer siteId;

        private String siteName;

        private String siteAddress;

        private String siteLeader;

        private String siteTel;

        private String siteStatus;

        private String userId;
    }
}


