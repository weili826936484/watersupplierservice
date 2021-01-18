package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.Column;
import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: sys_customer
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
@Table(name = "sys_customer")
@Data
public class SysCustomerPo {

    @ID
    @Column(name = "customer_id")
    private Integer customerId;
    private String customerName;
    private String customerTel;
    private String customerAddress;
    private Integer consumeCount;
    private Long consumeMoney;
    private String platformSource;
    private String platformUserid;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}