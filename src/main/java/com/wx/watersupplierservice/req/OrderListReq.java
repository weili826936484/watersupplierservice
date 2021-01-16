package com.wx.watersupplierservice.req;

import lombok.Data;

import java.util.List;

@Data
public class OrderListReq {

    /**
     * shopid 为null时表示全部
     */
    private Integer shopId;

    /**
     * 根据type字段，表示不同的状态,为null时表示全部
     */
    private String status;

    private Integer userId;

    /**
     * 为null时表示全部,不支持为空
     */
    private String platform;

    private Integer pageSize;

    private Integer pageIndex;

    private Integer offset;

    private Integer orderId;

    private List<Integer> idlist;

    private List<String> shoplist;

    private Integer orderBusinessId;
}
