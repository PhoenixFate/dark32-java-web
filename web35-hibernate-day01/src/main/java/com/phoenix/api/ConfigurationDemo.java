package com.phoenix.api;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * 学习configuration对象
 * Configuration功能：配置加载类
 *
 * 用于加载主配置以及orm元数据加载
 */
public class ConfigurationDemo {

    @Test
    public void fun1(){
        //1.创建，调用空参构造
        Configuration configuration=new Configuration();
        //2.读取指定配置文件; new Configuration()默认读取src下面的 hibernate.cfg.xml
        configuration.configure();

        //3.读取指定orm，但没用
        //configuration.addResource(resourceName);
        //configuration.addClass(persistentClass);

        //4.根据配置信息，创建SesssionFactory对象
        SessionFactory sessionFactory=configuration.buildSessionFactory();


    }



}
