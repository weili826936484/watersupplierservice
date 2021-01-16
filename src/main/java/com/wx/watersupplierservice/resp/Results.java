package com.wx.watersupplierservice.resp;

import lombok.Data;

import java.util.Objects;

@Data
public abstract class Results {

    public static final Integer OK = 1;
    public static final Integer ERROR = 0;
    public static final Integer NOAUTH = -1;
    public static final Integer INVALID = -3;
    public static final Integer NEEDED = 3;
    public static final Integer LIMIT = 2;
    public static final String ERROR_MESSAGE = "服务器错误";
    public static final String ERROR_PARAM= "参数缺失";
    public static final String SUCCESS_MESSAGE= "OK";
    public static final String ERROR_PERMISSION_DENIED="无权限执行此操作";


    protected Integer code;
    protected String message;
    //feign调用返回反序列化需要无参的构造器
    public Results() {
    }

    public boolean success(){

        return OK.equals(getCode());
    }
    /**
     * 业务正常
     * **/
    public static <T extends Results> boolean resultServiceOK(T results) {
        return results != null && Objects.equals(results.getCode(), OK) ;
    }
    /**
     * 业务异常
     * **/
    public static <T extends Results> boolean resultErrorException(T results) {
        return results != null && Objects.equals(results.getCode(), ERROR);
    }
}
