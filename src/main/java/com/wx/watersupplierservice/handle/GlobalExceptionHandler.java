package com.wx.watersupplierservice.handle;

import com.wx.watersupplierservice.resp.ObjectResults;
import com.wx.watersupplierservice.resp.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 验证提交的request错误处理（字段验证）
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public Results validErrorHandler(HttpServletRequest req, MethodArgumentNotValidException e){
        printLog(req,e);
        Map<String,String[]> paras= req.getParameterMap();

        for (Map.Entry<String,String[]> entry:paras.entrySet()){
            logger.error("key {}",entry.getKey());
            for (String s:entry.getValue()) {
                logger.error("values {}", s);
            }
        }

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        logger.error("",e);
        return new ObjectResults(Results.ERROR, tips,null);
    }

    /**
     * 用来处理bean validation异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Results handleConstraintViolationException(ConstraintViolationException e) {
        logger.error("",e);
        String message = "";
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if (!CollectionUtils.isEmpty(violations)) {
            StringBuilder msgBuilder = new StringBuilder();
            int count = 0;
            for (ConstraintViolation constraintViolation : violations) {
                if (count > 0) {
                    msgBuilder.append(",");
                }
                msgBuilder.append(constraintViolation.getMessage());
                count++;
            }
            message = msgBuilder.toString();
        }
        logger.error("", e);
        return new ObjectResults(Results.ERROR, message, null);
    }

    /**
     * 验证提交的request错误处理（字段验证ObjectId）
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public Results validErrorHandler(HttpServletRequest req, HttpMessageNotReadableException e){
        printLog(req, e);
        return new ObjectResults(Results.ERROR,e.getRootCause().getMessage(),null);
    }

    private void printLog(HttpServletRequest req, Exception e) {
        StringBuffer requestURL = req.getRequestURL();
        String queryString = req.getQueryString();
        String host = req.getHeader("Host");
        String userAgent = req.getHeader("User-Agent");
        //email:{},url:{},queryString:{},host:{},userAgent:{}
        logger.error("handler error log: {}",new Object[]{requestURL,queryString,host,userAgent},e);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Results jsonErrorHandler(HttpServletRequest req, Exception e) {
        printLog(req,e);
        return new ObjectResults(Results.ERROR,e.getMessage()==null?"服务器错误":e.getMessage(),null);
    }

}
