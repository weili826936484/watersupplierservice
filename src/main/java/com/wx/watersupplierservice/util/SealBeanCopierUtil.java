package com.wx.watersupplierservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.Field;
import java.util.*;

/**
 *  @author LS
 *  @version 1.0
 *  @date 2019/5/29
 **/
public class SealBeanCopierUtil {

    private static final Logger log = LoggerFactory.getLogger(SealBeanCopierUtil.class);

	/**
	 * 按照目标类名创建对象，并拷贝原对象属性
	 * 如果原对象为空，则将目标对象也置为空
	 * 该方法支持所有属性为基本类型，也支持List类型，但是List泛型必须为基本类型，否则泛型不能转换
	 * @param source 原对象
	 * @param targetClass 目标类名
	 */
	public static <T1 ,T2> T2  createCopy(T1 source,  Class<T2> targetClass){

		if(source==null){
			return null;
		}else{
			T2 target;
			try {
				target = targetClass.newInstance();
			} catch (InstantiationException e) {
				log.error(e.getMessage(), e);
				return null;
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
				return null;
			}
			BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
			beanCopier.copy(source,target,null);
			return target;
		}
	}

	/**
	 *
	 * 按照目标类名创建对象，并拷贝原对象属性
	 * 如果原对象为空，则将目标对象也置为空
	 * 该方法支持所有属性为基本类型，和对象类型，也支持List类型，但是List泛型必须为基本类型，否则泛型不能转换
	 * @param source 原对象
	 * @param targetClass 目标类名
	 * @param useConverter 是否使用convert 当属性包含不同对象时需要使用true
	 */
	public static <T1 ,T2> T2 createCopy(T1 source,  Class<T2> targetClass,boolean useConverter){

		if(source==null){
			return null;
		}else{
			T2 target;
			try {
				target = targetClass.newInstance();
			} catch (InstantiationException e) {
                log.error(e.getMessage(), e);
				return null;
			} catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
				return null;
			}
			BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, useConverter);
			SealConvert convert = null;
			if(useConverter){
				convert = SealConvert.getInstance();
			}
			beanCopier.copy(source,target,convert);
//			BeanUtils.copyVpsProperties(source, target);
			return target;
		}
	}

	/**
	 *
	 * 按照目标类名创建对象，并拷贝原对象属性
	 * 如果原对象为空，则将目标对象也置为空
	 * 该方法支持所有属性为基本类型，和对象类型，也支持List类型，但是List泛型可为非基本类型，属性需要在map中指明需要转换的对象类型
	 * @param source 原对象
	 * @param targetClass 目标类名
	 * @param convertMap List泛型中 属性对应转换的类型，map为空则只支持基本类型
	 */
	public static <T1 ,T2> T2 createCopy(T1 source, Class<T2> targetClass, Map<String,Class> convertMap,String... ignorProperties){

		if(source==null){
			return null;
		}else{
			T2 target;
			try {
				target = targetClass.newInstance();
			} catch (InstantiationException e) {
                log.error(e.getMessage(), e);
				return null;
			} catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
				return null;
			}
			if(null != convertMap || null != ignorProperties){

				BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, true);
				beanCopier.copy(source,target,SealConvert.getInstance(convertMap,ignorProperties));
			}else{
				BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
				beanCopier.copy(source,target,null);
			}
//			BeanUtils.copyVpsProperties(source, target);
			return target;
		}
	}

	/**
	 *
	 * 按照目标类名创建对象，并拷贝原对象属性
	 * 如果原对象为空，则将目标对象也置为空
	 * 该方法支持所有属性为基本类型，和对象类型，也支持List类型，但是List泛型可为非基本类型，属性需要在map中指明需要转换的对象类型
	 * @param source 原对象
	 * @param targetClass 目标类名
	 * @param convertMap List泛型中 属性对应转换的类型，map为空则只支持基本类型
	 */
	public static <T1 ,T2> T2 createCopy(T1 source, Class<T2> targetClass, Map<String,Class> convertMap){

		if(source==null){
			return null;
		}else{
			T2 target;
			try {
				target = targetClass.newInstance();
			} catch (InstantiationException e) {
                log.error(e.getMessage(), e);
				return null;
			} catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
				return null;
			}
			if(null != convertMap){

				BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, true);
				beanCopier.copy(source,target,SealConvert.getInstance(convertMap));
			}else{
				BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
				beanCopier.copy(source,target,null);
			}
//			BeanUtils.copyVpsProperties(source, target);
			return target;
		}
	}
	public static <T1 ,T2> T2 createCopy(T1 source, Class<T2> targetClass, Map<String,Class> convertMap,boolean circle){

		if(source==null){
			return null;
		}else{
			T2 target;
			try {
				target = targetClass.newInstance();
			} catch (InstantiationException e) {
                log.error(e.getMessage(), e);
				return null;
			} catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
				return null;
			}
			if(null != convertMap){

				BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, true);
				SealConvert instance = SealConvert.getInstance(convertMap);
				instance.setCircle(circle);
				beanCopier.copy(source,target, instance);
			}else{
				BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
				beanCopier.copy(source,target,null);
			}
//			BeanUtils.copyVpsProperties(source, target);
			return target;
		}
	}

	/**
	 *
	 * 按照目标类名创建对象，并拷贝原对象属性
	 * 如果原对象为空，则将目标对象也置为空
	 * @param source 原对象
	 * @param targetClass 目标类名
	 * @param beanCopier
	 * @param converter
	 */
	public static <T1 ,T2> T2 createCopy(T1 source, Class<T2> targetClass, BeanCopier beanCopier, Converter converter){

		if(source==null){
			return null;
		}else{
			T2 target;
			try {
				target = targetClass.newInstance();
			} catch (InstantiationException e) {
                log.error(e.getMessage(), e);
				return null;
			} catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
				return null;
			}

			beanCopier.copy(source,target,converter);
//			BeanUtils.copyVpsProperties(source, target);
			return target;
		}
	}

	/**
	 * 按照目标类名创建List，并拷贝原List中对象属性
	 * 如果原List为空，则返回为空
	 * 该方法只支持对象属性为基本类型和List的泛型为基本类型的对象
	 * @param sourceList 原对象List
	 * @param targetClass 目标类名
	 */
	public static <T1 ,T2> List<T2> createCopyList(List<T1> sourceList, Class<T2> targetClass){
		if(sourceList==null){
			return null;
		}else if(sourceList.size()==0){
			return new ArrayList<T2>();
		}else{
			List<T2> targetList=new ArrayList<T2>();
			BeanCopier beanCopier = null;
			for(Object source:sourceList){
				if(null == beanCopier){
					beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
				}
				T2 target = (T2)createCopy(source,targetClass,beanCopier,null);
				targetList.add(target);
			}
			return targetList;
		}
	}
	/**
	 * 按照目标类名创建List，并拷贝原List中对象属性
	 * 如果原List为空，则返回为空
	 * 该方法只支持对象属性为基本类型和List的泛型为基本类型的对象
	 * @param sourceList 原对象List
	 * @param targetClass 目标类名
	 */
	public static <T1 ,T2> List<T2> createCopyListUseBeanUtils(List<T1> sourceList, Class<T2> targetClass){
		if(sourceList==null){
			return null;
		}else if(sourceList.size()==0){
			return new ArrayList<T2>();
		}else{
			List<T2> targetList=new ArrayList<T2>();
			for(Object source:sourceList){
				try {
					T2 target = targetClass.newInstance();
					BeanUtils.copyProperties(source,target);
					targetList.add(target);
				} catch (InstantiationException e) {
                    log.error(e.getMessage(), e);
				} catch (IllegalAccessException e) {
                    log.error(e.getMessage(), e);
				}
			}
			return targetList;
		}
	}
	/**
	 * 按照目标类名创建List，并拷贝原List中对象属性
	 * 如果原List为空，则返回为空
	 * 该方法只支持对象属性为基本类型，对象类型和List的泛型为基本类型的对象
	 * @param sourceList 原对象List
	 * @param targetClass 目标类名
	 */
	public static <T1 ,T2> List<T2> createCopyList(List<T1> sourceList, Class<T2> targetClass,boolean useConvert){
		if(sourceList==null){
			return null;
		}else if(sourceList.size()==0){
			return new ArrayList<T2>();
		}else{
			List<T2> targetList=new ArrayList<T2>();
			BeanCopier beanCopier = null;
			SealConvert convert = null;
			for(Object source:sourceList){
				if(null == beanCopier){
					beanCopier = BeanCopier.create(source.getClass(), targetClass, useConvert);
				}
				if(useConvert){
					convert = SealConvert.getInstance();
				}
				T2 target = (T2)createCopy(source,targetClass,beanCopier,convert);
				targetList.add(target);
			}
			return targetList;
		}
	}

	/**
	 * 按照目标类名创建List，并拷贝原List中对象属性
	 * 如果原List为空，则返回为空
	 * 该方法支持对象属性为基本类型，对象类型和List
	 * @param sourceList 原对象List
	 * @param targetClass 目标类名
	 * @param convertMap List泛型对应的转换类型，key为属性名
	 * @param ignorProperties 忽略的属性
	 */
	public static <T1 ,T2> List<T2> createCopyList(List<T1> sourceList,  Class<T2> targetClass,Map<String,Class> convertMap,String... ignorProperties){

		if(sourceList==null){
			return null;
		}else if(sourceList.size()==0){
			return new ArrayList<T2>();
		}else{
			List<T2> targetList=new ArrayList<T2>();
			BeanCopier beanCopier = null;
			SealConvert convert = null;
			for(Object source:sourceList){
				if(null == beanCopier){
					boolean useConverter = null != convertMap || null != ignorProperties;
					if(useConverter){
						convert = SealConvert.getInstance(convertMap,ignorProperties);
					}
					beanCopier = BeanCopier.create(source.getClass(), targetClass, useConverter);
				}
				T2 target = (T2)createCopy(source,targetClass,beanCopier,convert);
				targetList.add(target);
			}
			return targetList;
		}

	}

	public static <T1 ,T2> List<T2> createCopyList(List<T1> sourceList,  Class<T2> targetClass,Map<String,Class> convertMap,boolean circle){

		if(sourceList==null){
			return null;
		}else if(sourceList.size()==0){
			return new ArrayList<T2>();
		}else{
			List<T2> targetList=new ArrayList<T2>();
			BeanCopier beanCopier = null;
			SealConvert convert = null;
			for(Object source:sourceList){
				if(null == beanCopier){
					boolean useConverter = null != convertMap;
					if(useConverter){
						convert = SealConvert.getInstance(convertMap);
						convert.setCircle(circle);
					}
					beanCopier = BeanCopier.create(source.getClass(), targetClass, useConverter);
				}
				T2 target = (T2)createCopy(source,targetClass,beanCopier,convert);
				targetList.add(target);
			}
			return targetList;
		}

	}

	/**
	 * 把对象放进map中的list
	 * @param map
	 * @param obj
	 * @param key
	 */
	public static <KEY,T> void putToMap(Map<KEY, List<T>> map, T obj,KEY key){
		if(map == null){
			map = new HashMap<KEY, List<T>>();
		}
		List<T> list = map.get(key);
		if(null == list){
			list = new ArrayList<T>();
		}
		list.add(obj);
		map.put(key, list);
	}

	/**
	 * 集合转成Map，使用属性作为key
	 * @param data
	 * @param field
	 * @param key
	 * @return
	 */
	public static <KEY,T> Map<KEY, List<T>> listToListMap(Collection<T> data, String field, Class<KEY> key){
		Map<KEY, List<T>> map = new HashMap<KEY, List<T>>();
		try {
			for (T t : data) {
				Field field2 = t.getClass().getDeclaredField(field);
				field2.setAccessible(true);
				Object object = field2.get(t);

				KEY o = key.cast(object);
				putToMap(map, t, o);
			}
		} catch (NoSuchFieldException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
		}

		return map;
	}

	/**
	 * 集合转成Map，使用属性作为key,多个属性作为key，以separate分隔
	 * @param data
	 * @param ignorCase
	 * @param separate
	 * @param fields
	 * @return
	 */
	public static <T> Map<String, List<T>> listToListMap(Collection<T> data, boolean ignorCase,String separate,String... fields){
		Map<String, List<T>> map = new HashMap<String, List<T>>();
		try {
			for (T t : data) {
				StringBuffer key = new StringBuffer();
				for (String field : fields) {
					Field field2 = t.getClass().getDeclaredField(field);
					field2.setAccessible(true);
					if(key.length() != 0 && null != separate){
						key.append(separate);
					}
					if (ignorCase) {
						key.append((String.valueOf(field2.get(t))).toLowerCase());
					}else {
						key.append((String.valueOf(field2.get(t))));

					}
				}
				putToMap(map, t, key.toString());
			}
		} catch (NoSuchFieldException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
		}

		return map;
	}

	/**
	 * 把对象放进map中的list
	 * @param map
	 * @param obj
	 * @param key
	 */
	public static <T> void putToMap(Map<String, List<T>> map, T obj,String key){
		if(map == null){
			map = new HashMap<String, List<T>>();
		}
		List<T> list = map.get(key);
		if(null == list){
			list = new ArrayList<T>();
		}
		list.add(obj);
		map.put(key, list);
	}

	/**
	 * 集合转成Map，使用属性作为key
	 * @param data
	 * @param field
	 * @param key
	 * @return
	 */
	public static <KEY,T> Map<KEY, T> listToMap(Collection<T> data,String field, Class<KEY> key){
		Map<KEY, T> map = new HashMap<KEY, T>();
		if(null==data){
			return map;
		}
		try {
			for (T t : data) {
				Field field2 = t.getClass().getDeclaredField(field);
				field2.setAccessible(true);
				Object object = field2.get(t);

				KEY o = key.cast(object);

				map.put(o, t);
			}
		} catch (NoSuchFieldException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
		}

		return map;
	}

	/**
	 * 集合转成Map，使用属性作为key
	 * @param data
	 * @param field
	 * @param ignorCase
	 * @return
	 */
	public static <T> Map<String, T> listToMap(Collection<T> data,String field, boolean ignorCase){
		Map<String, T> map = new HashMap<String, T>();
		if(null==data){
			return map;
		}
		try {
			for (T t : data) {
				Field field2 = t.getClass().getDeclaredField(field);
				field2.setAccessible(true);
				Object object = field2.get(t);

				String o = object.toString();
				if(ignorCase){
					map.put(o.toLowerCase(), t);
				}else{
					map.put(o, t);
				}
			}
		} catch (NoSuchFieldException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
		}

		return map;
	}

	/**
	 * 集合转成Map，使用多个属性及分隔符作为key
	 * @param data
	 * @param separate
	 * @param fields
	 * @return
	 */
	public static <T> Map<String, T> listToMap(Collection<T> data,String separate,String... fields){
		return listToMap(data, false, separate, fields);
	}

	/**
	 * 集合转成Map，使用多个属性及分隔符作为key
	 * @param data
	 * @param separate
	 * @param fields
	 * @return
	 */
	public static <T> Map<String, T> listToMap(Collection<T> data,boolean ignorCase,String separate,String... fields){
		Map<String, T> map = new HashMap<String, T>();
		if(null==data){
			return map;
		}
		try {
			for (T t : data) {
				StringBuffer key = new StringBuffer();
				for (String field : fields) {
					Field field2 = t.getClass().getDeclaredField(field);
					field2.setAccessible(true);
					if(key.length() != 0 && null != separate){
						key.append(separate);
					}
					if (ignorCase) {
						key.append((String.valueOf(field2.get(t))).toLowerCase());
					}else {
						key.append((String.valueOf(field2.get(t))));

					}
				}

				map.put(key.toString(), t);
			}
		} catch (NoSuchFieldException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
		}

		return map;
	}

	/**
	 *
	 * @param data
	 * @param field
	 * @param valueField
	 * @param ignorCase
	 * @return
	 */
	public static <T,V> Map<String, V> listToMap(Collection<T> data,String field,String valueField, boolean ignorCase){
		Map<String, V> map = new HashMap<String, V>();
		if(null==data){
			return map;
		}
		try {
			for (T t : data) {
				//key
				Field field2 = t.getClass().getDeclaredField(field);
				field2.setAccessible(true);
				Object object = field2.get(t);

				//value
				Field field1 = t.getClass().getDeclaredField(valueField);
				field1.setAccessible(true);
				Object object1 = field1.get(t);

				String o = object.toString();
//				String o1 = object1.toString();

				if(ignorCase){
					map.put(o.toLowerCase(), (V)object1);
				}else{
					map.put(o, (V)object1);
				}
			}
		} catch (NoSuchFieldException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
		}catch (ClassCastException e) {
            log.error(e.getMessage(), e);
		}

		return map;
	}

}
