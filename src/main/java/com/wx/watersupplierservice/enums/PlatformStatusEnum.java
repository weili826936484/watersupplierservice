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
public enum PlatformStatusEnum {
    PLANTFORM_JD("JDDJ","京东到家"),
    PLANTFORM_MT("MEITUAN","美团"),
    PLANTFORM_ELM("ELEME","饿了么"),
    PLANTFORM_SJZJ("SJZJ","商家自建");

    private String code;
    private String name;

    public static List<StatusDto> getPlatformStatus() {
        List<StatusDto> sorts = new ArrayList<>();
        for (PlatformStatusEnum siteStatusEnum : PlatformStatusEnum.values()) {
            StatusDto statusDto = new StatusDto();
            statusDto.setCode(siteStatusEnum.getCode());
            statusDto.setName(siteStatusEnum.getName());
            sorts.add(statusDto);
        }
        return sorts;
    }

    public static String getName(String code){
        for (PlatformStatusEnum value : values()) {
            if(value.code.equals(code)){
                return value.getName();
            }
        }
        return null;
    }
}
