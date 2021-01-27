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
    PLANTFORM_JD("JDDJ","JD"),
    PLANTFORM_MT("MEITUAN","MT"),
    PLANTFORM_ELM("ELEME","ELME"),
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

    public static boolean isPLANTFORM_JD(String code){
        return PLANTFORM_JD.code.equals(code);
    }
    public static boolean isPLANTFORM_MT(String code){
        return PLANTFORM_MT.code.equals(code);
    }
    public static boolean isPLANTFORM_ELM(String code){
        return PLANTFORM_ELM.code.equals(code);
    }
    public static boolean isPLANTFORM_SJZJ(String code){
        return PLANTFORM_SJZJ.code.equals(code);
    }
}
