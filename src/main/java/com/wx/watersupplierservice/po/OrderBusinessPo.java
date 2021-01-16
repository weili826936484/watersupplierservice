package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import lombok.Data;

/**
 * @Description: order_business
 * @author 82693
 * @date 2021年01月11日
 */
@Table(name = "order_business")
@Data
public class OrderBusinessPo {
    @ID
    private Integer id;
    private Integer orderId;
    private String platform;
    private String optCode;
    private Integer siteId;
    private Integer sendBy;
    private Integer createBy;
    private Date createTime;
    private Integer updateBy;
    private Date updateTime;

}