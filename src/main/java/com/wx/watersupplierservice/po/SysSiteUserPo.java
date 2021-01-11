package com.wx.watersupplierservice.po;


import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_site_user
 * @author 82693
 * @date 2021年01月11日
 */
@ApiModel
@Table(name = "sys_site_user")
public class SysSiteUserPo implements Serializable {
	private static final long serialVersionUID = 1L;

    @ID
    @Identity
    @ApiModelProperty(value = "主键")
    private Integer siteUserId;
    @ApiModelProperty(value = "水站id")
    private Integer siteId;
    @ApiModelProperty(value = "人员id")
    private Integer userId;

    public Integer getSiteUserId() {
        return siteUserId;
    }

    public void setSiteUserId(Integer siteUserId) {
        this.siteUserId = siteUserId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}