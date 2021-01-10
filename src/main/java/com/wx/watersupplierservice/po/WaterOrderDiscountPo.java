package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: water_order_discount
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
@Table(name = "water_order_discount")
public class WaterOrderDiscountPo implements Serializable {

    @ID
    @Identity
    @ApiModelProperty(value = "主键")
    private Integer discountId;
    @ApiModelProperty(value = "订单号")
    private String orderid;
    @ApiModelProperty(value = "调整单记录id（0:原单商品明细;非0:调整单id 或者 确认单id)")
    private String adjustid;
    @ApiModelProperty(value = "记录参加活动的sku数组")
    private String skuids;
    @ApiModelProperty(value = "优惠类型")
    private String discounttype;
    @ApiModelProperty(value = "小优惠类型")
    private String discountdetailtype;
    @ApiModelProperty(value = "用户领券编号")
    private String discountcode;
    @ApiModelProperty(value = "优惠金额")
    private String discountprice;
    @ApiModelProperty(value = "商家承担金额，有运费券（discountType=15）或单品免运（discountType=16）")
    private String venderpaymoney;
    @ApiModelProperty(value = "平台承担金额，有运费券（discountType=15）或单品免运（discountType=16）")
    private String platpaymoney;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAdjustid() {
        return adjustid;
    }

    public void setAdjustid(String adjustid) {
        this.adjustid = adjustid;
    }

    public String getSkuids() {
        return skuids;
    }

    public void setSkuids(String skuids) {
        this.skuids = skuids;
    }

    public String getDiscounttype() {
        return discounttype;
    }

    public void setDiscounttype(String discounttype) {
        this.discounttype = discounttype;
    }

    public String getDiscountdetailtype() {
        return discountdetailtype;
    }

    public void setDiscountdetailtype(String discountdetailtype) {
        this.discountdetailtype = discountdetailtype;
    }

    public String getDiscountcode() {
        return discountcode;
    }

    public void setDiscountcode(String discountcode) {
        this.discountcode = discountcode;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public String getVenderpaymoney() {
        return venderpaymoney;
    }

    public void setVenderpaymoney(String venderpaymoney) {
        this.venderpaymoney = venderpaymoney;
    }

    public String getPlatpaymoney() {
        return platpaymoney;
    }

    public void setPlatpaymoney(String platpaymoney) {
        this.platpaymoney = platpaymoney;
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