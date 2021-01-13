package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import lombok.Data;

/**
 * @Description: sys_weixin
 * @author 82693
 * @date 2021年01月10日
 */
@Table(name = "sys_weixin")
@Data
public class SysWeixinPo {

    @ID
    @Identity
    private String openid;
    private String nickname;
    private String headimgurl;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}