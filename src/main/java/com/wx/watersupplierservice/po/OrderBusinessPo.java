package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: order_business
 * @author 82693
 * @date 2021年01月11日
 */
@ApiModel
@Table(name = "order_business")
public class OrderBusinessPo implements Serializable {
	private static final long serialVersionUID = 1L;
    @ID
    @Identity
    @ApiModelProperty(value = "主键")
    private Integer businessId;
    @ApiModelProperty(value = "订单表")
    private Integer orderId;
    @ApiModelProperty(value = "流程代码")
    private String optCode;
    @ApiModelProperty(value = "水站表")
    private Integer siteId;
    @ApiModelProperty(value = "送水人员")
    private Integer sendBy;
    @ApiModelProperty(value = "创建人")
    private Integer createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private Integer updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getSendBy() {
        return sendBy;
    }

    public void setSendBy(Integer sendBy) {
        this.sendBy = sendBy;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}