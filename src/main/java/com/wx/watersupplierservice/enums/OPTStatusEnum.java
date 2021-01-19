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
public enum OPTStatusEnum {
    SITE_REFUSE("L21","水站拒单"),
    SITE_JIEDAN("L20","水站接单配送"),
    SITE_FENDAN("L10","分单"),
    SITE_CANCEL_REQ("B91","订单取消申请"),
    SITE_CANCEL_RETUIRN("B92","驳回取消申请"),
    SITE_CANCEL("B90","订单取消/商家同意取消申请"),
    SITE_OK("L90","配送完成");

    private String code;
    private String name;

    public static List<StatusDto> getSiteStatus() {
        List<StatusDto> sorts = new ArrayList<>();
        for (OPTStatusEnum siteStatusEnum : OPTStatusEnum.values()) {
            StatusDto statusDto = new StatusDto();
            statusDto.setCode(siteStatusEnum.getCode());
            statusDto.setName(siteStatusEnum.getName());
            sorts.add(statusDto);
        }
        return sorts;
    }

    public static String getName(String code){
        for (OPTStatusEnum value : values()) {
            if(value.code.equals(code)){
                return value.getName();
            }
        }
        return null;
    }

    public static boolean isSITE_REFUSE(String code){
        return SITE_REFUSE.code.equals(code);
    }
    public static boolean isSITE_JIEDAN(String code){ return SITE_JIEDAN.code.equals(code); }
    public static boolean isSITE_FENDAN(String code) { return SITE_FENDAN.code.equals(code); }
    public static boolean isSITE_CANCEL(String code){ return SITE_CANCEL.code.equals(code); }
    public static boolean isSITE_OK(String code){ return SITE_OK.code.equals(code); }
    public static boolean isSITE_CANCEL_RETUIRN(String code){ return SITE_CANCEL_RETUIRN.code.equals(code); }
}
