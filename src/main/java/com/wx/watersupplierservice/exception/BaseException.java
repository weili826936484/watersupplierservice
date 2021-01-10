package com.wx.watersupplierservice.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
    private Integer serviceCode;//返回业务code
    public BaseException(String msg){
        super(msg);
    }
}
