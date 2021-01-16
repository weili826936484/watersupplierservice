package com.wx.watersupplierservice.po;


import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_shop_user
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
@Table(name = "sys_shop_user")
public class SysShopUserPo {

    @ID
    @Identity
    @ApiModelProperty(value = "主键")
    private Integer shopUserId;
    @ApiModelProperty(value = "")
    private Integer shopId;
    @ApiModelProperty(value = "")
    private Integer userId;

    public Integer getShopUserId() {
        return shopUserId;
    }

    public void setShopUserId(Integer shopUserId) {
        this.shopUserId = shopUserId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}