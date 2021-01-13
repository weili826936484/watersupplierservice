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
public enum SiteStatusEnum {
    SITE_REFUSE("L21","水站拒单"),
    SITE_JIEDAN("L20","水站接单配送"),
    SITE_FENDAN("L10","分单"),
    SITE_OK("L90","配送完成");

    private String code;
    private String name;

    public static List<StatusDto> getSiteStatus() {
        List<StatusDto> sorts = new ArrayList<>();
        for (SiteStatusEnum siteStatusEnum : SiteStatusEnum.values()) {
            StatusDto statusDto = new StatusDto();
            statusDto.setCode(siteStatusEnum.getCode());
            statusDto.setName(siteStatusEnum.getName());
            sorts.add(statusDto);
        }
        return sorts;
    }

    public static String getName(String code){
        for (SiteStatusEnum value : values()) {
            if(value.code.equals(code)){
                return value.getName();
            }
        }
        return null;
    }
}
