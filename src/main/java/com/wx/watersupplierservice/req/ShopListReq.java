package com.wx.watersupplierservice.req;

import lombok.Data;

@Data
public class ShopListReq {

    /**
     * shopid 为0时表示全部
     */
    private Integer shopId;

    /**
     * 根据type字段，表示不同的状态,为0表示全部
     */
    private String status;

    private Integer userId;

    /**
     * 为0时表示全部
     */
    private String platform;
}
