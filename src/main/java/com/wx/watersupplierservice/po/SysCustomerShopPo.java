package com.wx.watersupplierservice.po;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_customer_shop
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
public class SysCustomerShopPo implements Serializable {
	private static final long serialVersionUID = 1L;

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