package com.wx.watersupplierservice.po;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_shop
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
public class SysShopPo implements Serializable {
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门店id")
    private Integer shopId;
    @ApiModelProperty(value = "商家id")
    private Integer orgId;
    @ApiModelProperty(value = "注册平台")
    private String platform;
    @ApiModelProperty(value = "门店编码")
    private String shopCode;
    @ApiModelProperty(value = "门店名称")
    private String shopName;
    @ApiModelProperty(value = "门店地址")
    private String shopAddress;
    @ApiModelProperty(value = "门店联系人")
    private String shopLeader;
    @ApiModelProperty(value = "联系方式")
    private String shopTel;
    @ApiModelProperty(value = "营业状态： 1营业 2休息中")
    private String shopStatus;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopLeader() {
        return shopLeader;
    }

    public void setShopLeader(String shopLeader) {
        this.shopLeader = shopLeader;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
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