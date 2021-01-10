package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: water_order_product
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
@Table(name = "water_order_product")
public class WaterOrderProductPo implements Serializable {

    @ID
    @Identity
    @ApiModelProperty(value = "主键")
    private Integer productId;
    @ApiModelProperty(value = "订单号")
    private String orderid;
    @ApiModelProperty(value = "到家商品编码")
    private String skuid;
    @ApiModelProperty(value = "商品规格，多规格之间用英文分号;分隔")
    private String skucostumeproperty;
    @ApiModelProperty(value = "调整单记录id（0:原单商品明细;非0:调整单id 或者 确认单id)")
    private String adjustid;
    @ApiModelProperty(value = "商品的名称")
    private String skuname;
    @ApiModelProperty(value = "商家商品编码")
    private String skuidisv;
    @ApiModelProperty(value = "到家商品销售价")
    private String skujdprice;
    @ApiModelProperty(value = "下单数量")
    private String skucount;
    @ApiModelProperty(value = "调整方式(0:默认值，没调整，原订单明细;1:新增;2:删除;3:修改数量)")
    private String adjustmode;
    @ApiModelProperty(value = "商品upc码")
    private String upccode;
    @ApiModelProperty(value = "到家商品门店价")
    private String skustoreprice;
    @ApiModelProperty(value = "到家商品成本价")
    private String skucostprice;
    @ApiModelProperty(value = "商品级别促销类型")
    private String promotiontype;
    @ApiModelProperty(value = "税率")
    private String skutaxrate;
    @ApiModelProperty(value = "促销活动编码")
    private String promotionid;
    @ApiModelProperty(value = "赠品关联的主商品信息，多个商品之间英文逗号分隔")
    private String relatedskus;
    @ApiModelProperty(value = "商品维度的单个商品重量（千克）")
    private String skuweight;
    @ApiModelProperty(value = "餐盒费（商品的总餐盒费）")
    private String canteenmoney;
    @ApiModelProperty(value = "商品扩展信息(当有返回值时为轻松购电子秤商品)")
    private String productextendinfomap;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

    public String getSkucostumeproperty() {
        return skucostumeproperty;
    }

    public void setSkucostumeproperty(String skucostumeproperty) {
        this.skucostumeproperty = skucostumeproperty;
    }

    public String getAdjustid() {
        return adjustid;
    }

    public void setAdjustid(String adjustid) {
        this.adjustid = adjustid;
    }

    public String getSkuname() {
        return skuname;
    }

    public void setSkuname(String skuname) {
        this.skuname = skuname;
    }

    public String getSkuidisv() {
        return skuidisv;
    }

    public void setSkuidisv(String skuidisv) {
        this.skuidisv = skuidisv;
    }

    public String getSkujdprice() {
        return skujdprice;
    }

    public void setSkujdprice(String skujdprice) {
        this.skujdprice = skujdprice;
    }

    public String getSkucount() {
        return skucount;
    }

    public void setSkucount(String skucount) {
        this.skucount = skucount;
    }

    public String getAdjustmode() {
        return adjustmode;
    }

    public void setAdjustmode(String adjustmode) {
        this.adjustmode = adjustmode;
    }

    public String getUpccode() {
        return upccode;
    }

    public void setUpccode(String upccode) {
        this.upccode = upccode;
    }

    public String getSkustoreprice() {
        return skustoreprice;
    }

    public void setSkustoreprice(String skustoreprice) {
        this.skustoreprice = skustoreprice;
    }

    public String getSkucostprice() {
        return skucostprice;
    }

    public void setSkucostprice(String skucostprice) {
        this.skucostprice = skucostprice;
    }

    public String getPromotiontype() {
        return promotiontype;
    }

    public void setPromotiontype(String promotiontype) {
        this.promotiontype = promotiontype;
    }

    public String getSkutaxrate() {
        return skutaxrate;
    }

    public void setSkutaxrate(String skutaxrate) {
        this.skutaxrate = skutaxrate;
    }

    public String getPromotionid() {
        return promotionid;
    }

    public void setPromotionid(String promotionid) {
        this.promotionid = promotionid;
    }

    public String getRelatedskus() {
        return relatedskus;
    }

    public void setRelatedskus(String relatedskus) {
        this.relatedskus = relatedskus;
    }

    public String getSkuweight() {
        return skuweight;
    }

    public void setSkuweight(String skuweight) {
        this.skuweight = skuweight;
    }

    public String getCanteenmoney() {
        return canteenmoney;
    }

    public void setCanteenmoney(String canteenmoney) {
        this.canteenmoney = canteenmoney;
    }

    public String getProductextendinfomap() {
        return productextendinfomap;
    }

    public void setProductextendinfomap(String productextendinfomap) {
        this.productextendinfomap = productextendinfomap;
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