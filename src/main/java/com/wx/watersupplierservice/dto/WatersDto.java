package com.wx.watersupplierservice.dto;

import lombok.Data;

/**
 * @program: WatersDto
 *
 * @description:
 *
 * @author: weili
 *
 * @create: 2021-01-11 23:08
 **/
@Data
public class WatersDto {
    private Integer siteUserId;
    private Integer userId;
    private String userName;
    private String userTel;
    private Integer orderCount;
}
