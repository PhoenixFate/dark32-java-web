package com.phoenix.shop.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 *将数据封装给JavaBean,可以处理时间格式
 */
public final class MyBeanUtils {

    /**
     * 将数据封装给JavaBean，支持时间类型转换
     * @param bean
     * @param properties
     */
    public static void populate(Object bean, Map<String,String[]> properties){
        try {
            //1. 创建BeanUtils提供时间转换器
            DateConverter dateConverter = new DateConverter();
            //2. 设置需要转换的格式
            dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","MM/dd/yyyy"});
            //3. 注册格式
            ConvertUtils.register(dateConverter, Date.class);
            //4. 封装数据
            BeanUtils.populate(bean, properties);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static<T> T populate(Class<T> beanClass, Map<String,String[]> properties){
        try {
            //1. 使用反射创建实例
            T bean =beanClass.newInstance();
            //2.1 创建BeanUtils提供时间转换器
            DateConverter dateConverter = new DateConverter();
            //2.2 设置需要转换的格式
            dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy-MM-dd HH:mm:ss","MM/dd/yyyy"});
            //2.3 注册格式
            ConvertUtils.register(dateConverter, Date.class);
            //3. 封装数据
            BeanUtils.populate(bean, properties);
            return bean;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

