package com.wx.watersupplierservice.po;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_shop_site
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
public class SysShopSitePo implements Serializable {
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer shopSiteId;
    @ApiModelProperty(value = "门店id")
    private Integer shopId;
    @ApiModelProperty(value = "水站id")
    private Integer siteId;

    public Integer getShopSiteId() {
        return shopSiteId;
    }

    public void setShopSiteId(Integer shopSiteId) {
        this.shopSiteId = shopSiteId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }


}