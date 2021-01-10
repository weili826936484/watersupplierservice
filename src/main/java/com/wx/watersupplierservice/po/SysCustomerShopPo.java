package com.wx.watersupplierservice.po;


import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_customer_shop
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
@Table(name = "sys_customer_shop")
public class SysCustomerShopPo implements Serializable {

    @ApiModelProperty(value = "消费者")
    private Integer customerId;
    @ApiModelProperty(value = "门店")
    private Integer shopId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }


}