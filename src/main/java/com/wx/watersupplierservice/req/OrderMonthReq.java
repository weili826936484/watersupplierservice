package com.wx.watersupplierservice.req;/**
 * @Project: watersupplierservice
 * @Package com.wx.watersupplierservice.req
 * @Description: TODO
 * @author : weili
 * @date Date : 2021年01月11日 23:01
 * @version V1.0
 */

import lombok.Data;

/**
 * @program: SendWatersReq
 *
 * @description: 商户每月统计情况
 *
 * @author: weili
 *
 * @create: 2021-01-11 23:01
 **/
@Data
public class OrderMonthReq {

    private Integer orgId;
    private String code;
    private Integer pageIndex;
    private Integer pageSize;
    private Integer offset;
}
