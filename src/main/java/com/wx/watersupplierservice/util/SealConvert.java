package com.wx.watersupplierservice.util;

import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lishuai17
 * @create 2019-05-27 16:53
 * @desc
 **/
@Data
public class SealConvert implements Converter {

    private Map<String,Class> map ;

    private String[] ignorProperties;

    private boolean circle;

    public SealConvert(){

    }

    public SealConvert(Map<String,Class> map){
        this.map = map;
    }
    public SealConvert(Map<String,Class> map, String[] ignorProperties){
        this.map = map;
        this.ignorProperties = ignorProperties;
    }

    public SealConvert(String[] ignorProperties){
        this.ignorProperties = ignorProperties;
    }

    @Override
    public Object convert(Object o, Class aClass, Object o1) {

        if(o == null){
            return null;
        }

        String o1Str = getProperties(o1);
        if(null != ignorProperties){
            for(String ignorProperty : ignorProperties){
                if(ignorProperty.equals(o1Str)){
                    return null;
                }

            }
        }

        if(null != map){
            if(map.containsKey(o1Str)){
                if(o instanceof List){
                    return getResultByList(o,map.get(o1Str));
                }else{
                    Class target = map.get(o1Str);
                    BeanCopier beanCopier = BeanCopier.create(o.getClass(), target, circle);
                    try {
                        Object result = target.newInstance();
                        beanCopier.copy(o, result,circle ? this : null);
                        return result;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return o;
                }
            }
        }

        if(!isWrapClass(aClass) && !aClass.isInstance(o)){
            BeanCopier beanCopier = BeanCopier.create(o.getClass(), aClass, circle);
            try {
                Object result = aClass.newInstance();
                beanCopier.copy(o, result,circle ? this : null);
                return result;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return o;
    }

    private String getProperties(Object o1) {
        String setStr = o1.toString().replaceAll("set", "");
        String firstChar = setStr.substring(0, 1);
        String substring = setStr.substring(1);
        return firstChar.toLowerCase() + substring;
    }

    private Object getResultByList(Object o,Class clz){
        List list = (List)o;
        List<Object> result = new ArrayList<>();
        BeanCopier beanCopier = null;
        for(Object sourceValue  : list){
            if(sourceValue instanceof List){
                Object resultByList = getResultByList(sourceValue, clz);
                result.add(resultByList);
            }else{
                if(null == beanCopier){
                    beanCopier = BeanCopier.create(sourceValue.getClass(), clz, circle);
                }
                Object o1 = null;
                try {
                    o1 = clz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                beanCopier.copy(sourceValue,o1,circle ? this : null);
                result.add(o1);


            }
        }

        return result;

    }

    public boolean isWrapClass(Class<?> clz) {
        try {
            return clz.isPrimitive() || (((Class<?>) clz.getField("TYPE").get(null)).isPrimitive())  ;
        } catch (Exception e) {
            return false;
        }
    }

    public static SealConvert getInstance(){
        return new SealConvert();
    }

    public static SealConvert getInstance(Map<String,Class> map){
        return new SealConvert(map);
    }

    public static SealConvert getInstance(Map<String,Class> map,String[] ignorProperties){
        return new SealConvert(map,ignorProperties);
    }
    public static SealConvert getInstance(String[] ignorProperties){
        return new SealConvert(ignorProperties);
    }
}
