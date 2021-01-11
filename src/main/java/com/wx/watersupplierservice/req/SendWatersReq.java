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
 * @description: 送水人员列表
 *
 * @author: weili
 *
 * @create: 2021-01-11 23:01
 **/
@Data
public class SendWatersReq {

    private Integer siteId;
    private Integer pageIndex;
    private Integer pageSize;
    private Integer offset;
}
