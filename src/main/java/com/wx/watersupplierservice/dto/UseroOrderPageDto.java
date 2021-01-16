package com.wx.watersupplierservice.dto;


import lombok.Data;

import java.util.List;

@Data
public class UseroOrderPageDto {
    private Integer count;
    private Integer pageSize;
    private Integer pageIndex;
    private List<OrderDto> list;
}
