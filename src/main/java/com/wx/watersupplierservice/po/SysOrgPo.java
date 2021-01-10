package com.wx.watersupplierservice.po;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: sys_org
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
public class SysOrgPo implements Serializable {
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer orgId;
    @ApiModelProperty(value = "商家平台")
    private String platform;
    @ApiModelProperty(value = "商家编码")
    private String orgcode;
    @ApiModelProperty(value = "商家联系人")
    private String orgLeader;
    @ApiModelProperty(value = "token")
    private String token;
    @ApiModelProperty(value = "appKey")
    private String appKey;
    @ApiModelProperty(value = "appSecret")
    private String appSecret;
    @ApiModelProperty(value = "电话")
    private String orgTel;
    @ApiModelProperty(value = "邮箱")
    private String orgMail;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgLeader() {
        return orgLeader;
    }

    public void setOrgLeader(String orgLeader) {
        this.orgLeader = orgLeader;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    public String getOrgMail() {
        return orgMail;
    }

    public void setOrgMail(String orgMail) {
        this.orgMail = orgMail;
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