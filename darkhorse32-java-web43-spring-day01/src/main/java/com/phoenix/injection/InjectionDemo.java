package com.phoenix.injection;

import com.phoenix.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectionDemo {

    /**
     * set方法注入
     */
    @Test
    public void test01(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/injection/applicationContext.xml");
        User user5 = (User) applicationContext.getBean("user5");
        System.out.println(user5);
    }

    /**
     * 构造函数注入
     */
    @Test
    public void test02(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/injection/applicationContext.xml");
        User user6 = (User) applicationContext.getBean("user6");
        System.out.println(user6);
    }

    /**
     * 构造函数注入
     */
    @Test
    public void test03(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/injection/applicationContext.xml");
        User user6 = (User) applicationContext.getBean("user6");
        System.out.println(user6);
    }


    /**
     * p命名空间注入
     */
    @Test
    public void test04(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/injection/applicationContext.xml");
        User user7 = (User) applicationContext.getBean("user7");
        System.out.println(user7);
    }

    /**
     * spel注入注入
     */
    @Test
    public void test05(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/injection/applicationContext.xml");
        User user8 = (User) applicationContext.getBean("user8");
        System.out.println(user8);
    }


    /**
     * 复杂类型注入，array
     */
    @Test
    public void test06(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/injection/applicationContext.xml");
        CollectionBean collectionBean = (CollectionBean) applicationContext.getBean("collectionBean");
        System.out.println(collectionBean);
    }
}
