package com.wx.watersupplierservice.resp;

import com.wx.watersupplierservice.exception.PublicException;
import lombok.Data;

/**
 * @author lishuai17
 * @create 2019-03-22 17:34
 * @desc
 **/
@Data
public class ObjectResults<T> extends Results {

    T data;

    public ObjectResults(){

    }

    public ObjectResults(int status, String message, T data) {
        this.code = status;
        this.message = message;
        this.data = data;
    }

    public static <E> ObjectResults<E> createSuccessResult() {
        return new ObjectResults<>(Results.OK, Results.SUCCESS_MESSAGE, null);
    }

    public static <E> ObjectResults<E> createSuccessResult(E data) {
        return new ObjectResults<>(Results.OK, Results.SUCCESS_MESSAGE, data);
    }
    public static <E> ObjectResults<E> createSuccessResult(E data, String message) {
        return new ObjectResults<>(Results.OK, message, data);
    }
    public static <E> ObjectResults<E> createErrorResult(E data) {
        return new ObjectResults<>(Results.ERROR, Results.ERROR_MESSAGE, data);
    }

    public static <E> ObjectResults<E> createErrorResult(E data, String message) {
        return new ObjectResults<>(Results.ERROR, message, data);
    }

    public static <T> T  handlerResultsData(ObjectResults results, String methodDeclaraction) {
        if (Results.resultServiceOK(results)) {
            return (T)results.getData();
        } else {
            throw new PublicException(results.getMessage());
        }
    }
    public static <E> ObjectResults<E> createErrorResultCompatible (E data,Exception e,String errorMessage) {
        return  new ObjectResults(Results.ERROR,e.getMessage()==null?errorMessage:e.getMessage(),data);
    }

    public static <T> ObjectResults<T> successResult(){
        return new ObjectResults<>(Results.OK, Results.SUCCESS_MESSAGE, null);
    }

    public static <T> ObjectResults<T> errorResult(){
        return errorResult(Results.ERROR_MESSAGE);
    }

    public static <T> ObjectResults<T> errorResult(String message){
        return new ObjectResults<>(Results.ERROR, message, null);
    }

    public static <T> T  handlerResultsData(ObjectResults results) {
        if (Results.resultServiceOK(results)) {
            return (T)results.getData();
        } else {
            throw new PublicException(results.getMessage());
        }
    }
}
