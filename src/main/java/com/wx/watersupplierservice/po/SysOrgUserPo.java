package com.wx.watersupplierservice.po;


import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_org_user
 * @author 82693
 * @date 2021年01月11日
 */
@ApiModel
@Table(name = "sys_org_user")
public class SysOrgUserPo implements Serializable {
	private static final long serialVersionUID = 1L;

    @ID
    @Identity
    @ApiModelProperty(value = "主键")
    private Integer orgUserId;
    @ApiModelProperty(value = "商家id")
    private Integer orgId;
    @ApiModelProperty(value = "人员id")
    private Integer userId;

    public Integer getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(Integer orgUserId) {
        this.orgUserId = orgUserId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


}