package com.wx.watersupplierservice.req;

import lombok.Data;

import java.util.List;

@Data
public class ChangeOrderReq {
    private List<Integer> orderIds;
    private String optCode;
    private String remark;
    private Integer userId;
    /**
     * 分单状态才传
     */
    private Integer siteId;
}
