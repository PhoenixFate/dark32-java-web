package com.phoenix.injection;

import com.phoenix.bean.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo3 {

    private ApplicationContext applicationContext;

    @Before
    public void createContext() {
        applicationContext = new ClassPathXmlApplicationContext("com/phoenix/injection/applicationContext.xml");
    }


    /**
     * set注入
     */
    @Test
    public void fun1() {
        //测试创建对象方式1
        User user5 = (User) applicationContext.getBean("user5");
        System.out.println(user5);
    }

    /**
     * 构造注入
     */
    @Test
    public void fun2() {
        //测试创建对象方式1
        User user6 = (User) applicationContext.getBean("user6");
        System.out.println(user6);
    }

    /**
     * p名称空间注入
     */
    @Test
    public void fun3() {
        //测试创建对象方式1
        User user7 = (User) applicationContext.getBean("user7");
        System.out.println(user7);
    }

    /**
     *  spel注入: spring Expression Language spring表达式语言
     */
    @Test
    public void fun4() {
        //测试创建对象方式1
        User user8 = (User) applicationContext.getBean("user8");
        System.out.println(user8);
    }

    /**
     *  复杂类型注入
     */
    @Test
    public void fun5() {
        //测试创建对象方式1
        CollectionBean collectionBean = (CollectionBean) applicationContext.getBean("collectionBean");
        System.out.println(collectionBean);
    }


}
