package com.wx.watersupplierservice.req;

import lombok.Data;

@Data
public class ShopsReq {
    private Integer userId;
    private String shopName;
    private String status;
}
