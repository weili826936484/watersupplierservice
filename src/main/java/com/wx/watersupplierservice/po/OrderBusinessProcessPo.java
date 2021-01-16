package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.Column;
import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: order_business_process
 * @author 82693
 * @date 2021年01月11日
 */
@ApiModel
@Table(name = "order_business_process")
@Data
public class OrderBusinessProcessPo {

    @ID
    @Column(name = "process_id")
    @ApiModelProperty(value = "主键")
    private Integer processId;
    @ApiModelProperty(value = "业务表")
    @Column(name = "business_id")
    private Integer businessId;
    @ApiModelProperty(value = "")
    private String optCode;
    @ApiModelProperty(value = "反馈结果信息描述")
    private String resultInfo;
    @ApiModelProperty(value = "创建人")
    private Integer createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private Integer updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}