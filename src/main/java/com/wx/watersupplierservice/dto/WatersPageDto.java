package com.wx.watersupplierservice.dto;

import lombok.Data;

import java.util.List;

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
public class WatersPageDto{
    private Integer count;
    private Integer pageIndex;
    private Integer pageSize;
    private List<WatersDto> list;

    @Data
    public static class WatersDto {
        private Integer siteUserId;
        private Integer userId;
        private String userName;
        private String userTel;
        private Integer orderCount;
    }
}

