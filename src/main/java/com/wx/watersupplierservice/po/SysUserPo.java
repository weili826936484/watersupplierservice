package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: sys_user
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
@Table(name = "sys_user")
@Data
public class SysUserPo {

    @ID
    @Identity
    @ApiModelProperty(value = "主键")
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String userCode;
    @ApiModelProperty(value = "姓名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "角色")
    private String roleCode;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "电话")
    private String userTel;
    @ApiModelProperty(value = "人员状态 1在职 2 离职")
    private Integer userStatus;
    @ApiModelProperty(value = "微信信息")
    private String openid;
    @ApiModelProperty(value = "商家id")
    private Integer orgId;
    @ApiModelProperty(value = "店铺id")
    private Integer shopId;
    @ApiModelProperty(value = "水站id")
    private Integer siteId;
    @ApiModelProperty(value = "推送微信信息 1推送 2不推送")
    private String sendWeixin;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}