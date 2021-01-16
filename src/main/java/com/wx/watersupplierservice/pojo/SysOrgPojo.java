package com.wx.watersupplierservice.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysOrgPojo {
    private Integer orgUserId;
    private Integer orgId;
    private String orgcode;
    private String platform;
    private String token;
    private String appKey;
    private String appSecret;
}
