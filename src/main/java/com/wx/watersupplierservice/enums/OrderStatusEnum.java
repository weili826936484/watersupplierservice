package com.wx.watersupplierservice.enums;

import com.wx.watersupplierservice.dto.StatusDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : weili
 * @version V1.0
 * @Project: watersupplierservice
 * @Package com.wx.watersupplierservice
 * @Description: TODO
 * @date Date : 2021年01月12日 0:18
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderStatusEnum {
    ORDER_LOCK("20010","订单锁定"),
    ORDER_CANCEL_REQ("20020","订单取消申请"),
    ORDER_CANCELED("20030","订单取消"),
    ORDER_NOT_PAY("20040","超时未支付系统取消"),
    ORDER_WAIT_PAY("31000","等待付款"),
    ORDER_PAY("31020","已付款"),
    ORDER_PRE_DEAL("41000","待处理"),
    ORDER_OUT("32000","等待出库"),
    ORDER_SEND("33040","配送中"),
    ORDER_RECEIVE("33060","已妥投"),
    ORDER_OK("90000","订单完成");

    private String code;
    private String name;

    public static List<StatusDto> getOrderStatus() {
        List<StatusDto> sorts = new ArrayList<>();
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            StatusDto statusDto = new StatusDto();
            statusDto.setCode(orderStatusEnum.getCode());
            statusDto.setName(orderStatusEnum.getName());
            sorts.add(statusDto);
        }
        return sorts;
    }

    public static String getName(String code){
        for (OrderStatusEnum value : values()) {
            if(value.code.equals(code)){
                return value.getName();
            }
        }
        return null;
    }
    public static boolean isORDER_OUT(String code){
        return ORDER_OUT.code.equals(code);
    }

}
