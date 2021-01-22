package com.wx.watersupplierservice.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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

    private List<String> platforms;

    private String search;

    private String searchAddress;

    private String searchName;

    private Integer pageSize;

    private Integer pageIndex;

    private Integer offset;

    private Integer orderId;

    private List<Integer> idlist;

    private List<String> shoplist;

    private Integer orderBusinessId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
}
