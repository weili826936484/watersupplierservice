package com.wx.watersupplierservice.exception;


import com.xdf.pscommon.mybatis.exception.BaseException;

public class PublicException extends BaseException {
    public PublicException(String message){
        super(message);
    }

    public static PublicException of(String message){
        return new PublicException(message);
    }

}
