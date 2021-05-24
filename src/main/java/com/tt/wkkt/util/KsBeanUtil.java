package com.tt.wkkt.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/
public class KsBeanUtil {

    /**
     * Copy List
     * @param source
     * @param target
     */
    public static void copyList(List source, List target){
        source.forEach(o -> {
            try {
                Object c = o.getClass().newInstance();
                BeanUtils.copyProperties(o, c);
                target.add(c);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static List copyListProperties(List source, Class clazz){
        List target = new ArrayList();
        source.forEach(o -> {
            try {
                target.add( JSONObject.parseObject(JSONObject.toJSONString(o),clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return target;
    }

    /**
     * Convert List Generic
     * @param source Source list
     * @param clazz Target list
     */
    public static <T> List<T> convertList(List source, Class<T> clazz){
        List<T> res = new ArrayList<>();
        source.forEach(o -> res.add(KsBeanUtil.copyPropertiesThird(o, clazz)));
        return res;
    }

    /**
     * Merged entity attribute values,Empty and do not cover
     * @param sourceObj Source object
     * @param targetObj Target
     */


    /**
     * Shallow copies of source and target objects
     * Note: The attribute names in the target object and the source object must be the same, otherwise they cannot be copied
     *
     * @param sourceObj Source object
     * @param targetObj Target
     */
    public static void copyPropertiesThird(Object sourceObj, Object targetObj) {
        BeanUtils.copyProperties(sourceObj, targetObj);
    }

    /**
     * Shallow copies of source and target objects
     * Note: The attribute names in the target object and the source object must be the same, otherwise they cannot be copied
     *
     * @param sourceObj Source object
     * @param clazz     Class object of the target object
     * @param <T>
     * @return          Target
     */
    public static <T> T copyPropertiesThird(Object sourceObj,  Class<T> clazz) {
        T c = null;
        try {
            c = clazz.newInstance();
            KsBeanUtil.copyPropertiesThird(sourceObj, c);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Used for deep copy of different attribute types in source object and target object
     * Note: The attribute names in the target object and the source object must be the same, otherwise they cannot be copied
     * Test examples can refer to
     * @param source        Source object
     * @param targetClass   Class object of the target object
     * @param <T>
     * @return              Target
     */
    public static <T> T convert(Object source, Class<T> targetClass, SerializerFeature... features) {
        String sourceJsonStr = JSONObject.toJSONString(source,features);
        T target = JSONObject.parseObject(sourceJsonStr, targetClass);
        return target;
    }

    /**
     * Used for deep conversion between source collection and target collection
     * Note: The attribute names in the target collection object and the source collection object must be the same, otherwise they cannot be copied
     * Test examples can refer to
     * @param sourceList    Source collection
     * @param targetClass   Class object of the target object
     * @param <S>           Source collection object type
     * @param <T>           Target collection object type
     * @return              Target set
     */
    public static <S,T> List<T> convert(List<S> sourceList, Class<T> targetClass,SerializerFeature... features) {
        return sourceList.stream().map(s -> convert(s, targetClass,features)).collect(Collectors.toList());
    }

    /**
     * Used for deep conversion between source pagination collection and target pagination collection
     * important point：The attribute names in the target paging element object and the source paging element object must be consistent，Otherwise it cannot be copied
     * Test examples can refer to {@link com.deloitte.fgs.convert.ConvertUtils}
     * @param sourcePage    Source page collection
     * @param targetClass   Class object in the target element object
     * @param <S>           Element type in the source paging object
     * @param <T>           Element type in the target pagination object
     * @return              Target pagination collection
     */


    /**
     * Get all attributes of the class (including the attributes of the parent class)
     * @param cls class
     * @param fields Property List
     * @return All fields
     */
    private static List<Field> getBeanFields(Class cls, List<Field> fields){
        fields.addAll(Arrays.asList(cls.getDeclaredFields()));
        if(Objects.nonNull(cls.getSuperclass())){
            fields = getBeanFields(cls.getSuperclass() , fields);
        }
        return fields;
    }



    /**
     * Use Introspector, map collection into javabean
     *
     * @param map       map
     * @param beanClass bean Class
     * @return bean object
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> beanClass) {

        if (MapUtils.isEmpty(map)) {
            return null;
        }

        try {
            T t = beanClass.newInstance();

            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    setter.invoke(t, map.get(property.getName()));
                }
            }
            return t;
        } catch (Exception ex) {
            // log.error("########Error when converting map collection to javabean######，Error message，{}", ex.getMessage());getMessage
            throw new RuntimeException();
        }

    }

    /**
     * Using Introspector, the object is converted to a map collection
     *
     * @param beanObj javabean object
     * @return map collection
     */
    public static Map<String, Object> beanToMap(Object beanObj) {

        if (null == beanObj) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(beanObj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(beanObj) : null;
                map.put(key, value);
            }

            return map;
        } catch (Exception ex) {
            // log.error("########javabean collection to map error######，Error message，{}", ex.getMessage());
            throw new RuntimeException();
        }
    }


    /**
     * Convert List<T> to List<Map<String, Object>>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

}
