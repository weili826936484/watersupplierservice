package com.wx.watersupplierservice.req;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ChangeOrderReq {
    /**
     * key为orderid value为水站id
     */
    private List<Map<Integer,Integer>> orderSiteIds;
    private String optCode;
    private String remark;
    private Integer userId;
}
