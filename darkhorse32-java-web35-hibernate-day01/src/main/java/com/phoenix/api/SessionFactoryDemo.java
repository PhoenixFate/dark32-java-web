package com.phoenix.api;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * SessionFactory学习
 *
 * SessionFactory用于创建操作数据库核心对象 session对象的工厂  （简单说就是；创建session对象）
 * 注意 1.sessionFactory用于保存和使用所有配置信息
 *     2.sessionFactory是线程安全的对象
 *     3.session是操作数据库的核心对象
 *
 *
 * 结论 保存在web项目中，只创建一个sessionFactory
 *
 *
 *
 */
public class SessionFactoryDemo {

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

        //5.获得session
        Session session = sessionFactory.openSession();
        //获得一个于线程绑定的session
        Session currentSession = sessionFactory.getCurrentSession();



    }


}
