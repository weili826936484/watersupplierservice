package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.Column;
import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_customer
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
@Table(name = "sys_customer")
public class SysCustomerPo {

    @ID
    @ApiModelProperty(value = "主键")
    @Column(name = "customer_id")
    private Integer customerId;
    @ApiModelProperty(value = "顾客姓名")
    private String customerName;
    @ApiModelProperty(value = "消费者电话")
    private String customerTel;
    @ApiModelProperty(value = "顾客地址")
    private String customerAddress;
    @ApiModelProperty(value = "消费次数")
    private Integer consumeCount;
    @ApiModelProperty(value = "消费金额")
    private Integer consumeMoney;
    @ApiModelProperty(value = "来源平台")
    private String platformSource;
    @ApiModelProperty(value = "平台账户Id")
    private String platformUserid;
    @ApiModelProperty(value = "")
    private String createBy;
    @ApiModelProperty(value = "")
    private Date createTime;
    @ApiModelProperty(value = "")
    private String updateBy;
    @ApiModelProperty(value = "")
    private Date updateTime;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Integer getConsumeCount() {
        return consumeCount;
    }

    public void setConsumeCount(Integer consumeCount) {
        this.consumeCount = consumeCount;
    }

    public Integer getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(Integer consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public String getPlatformSource() {
        return platformSource;
    }

    public void setPlatformSource(String platformSource) {
        this.platformSource = platformSource;
    }

    public String getPlatformUserid() {
        return platformUserid;
    }

    public void setPlatformUserid(String platformUserid) {
        this.platformUserid = platformUserid;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}