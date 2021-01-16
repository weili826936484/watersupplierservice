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
public enum UserRoleEnum {
    ORG_MANAGER("ORG_MANAGER","商户"),
    SHOP_MANAGER("SHOP_MANAGER","门店"),
    SITE_MANAGER("SITE_MANAGER","水站"),
    SITE_SENDER("SITE_SENDER","配送员");

    private String code;
    private String name;

    public static List<StatusDto> getUserRoleStatus() {
        List<StatusDto> sorts = new ArrayList<>();
        for (UserRoleEnum siteStatusEnum : UserRoleEnum.values()) {
            StatusDto statusDto = new StatusDto();
            statusDto.setCode(siteStatusEnum.getCode());
            statusDto.setName(siteStatusEnum.getName());
            sorts.add(statusDto);
        }
        return sorts;
    }

    public static String getName(String code){
        for (UserRoleEnum value : values()) {
            if(value.code.equals(code)){
                return value.getName();
            }
        }
        return null;
    }

    /**
     * 判断角色
     * @param code
     * @return
     */
    public static boolean isORG_MANAGER(String code){
        return ORG_MANAGER.code.equals(code);
    }
    public static boolean isSHOP_MANAGER(String code){
        return SHOP_MANAGER.code.equals(code);
    }
    public static boolean isSITE_MANAGER(String code){
        return SITE_MANAGER.code.equals(code);
    }
    public static boolean isSITE_SENDER(String code){
        return SITE_SENDER.code.equals(code);
    }
}
