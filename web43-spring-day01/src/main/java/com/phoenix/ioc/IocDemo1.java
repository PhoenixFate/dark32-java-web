package com.phoenix.ioc;

import com.phoenix.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocDemo1 {

    @Test
    public void test01(){
        //1.创建容器对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");

        //2.向容器要对象
        Object user = applicationContext.getBean("user");
        Object myUser = applicationContext.getBean("myUser");
        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);

        //3.打印user对象
        System.out.println(user instanceof User);
        System.out.println(user);
        System.out.println(myUser);
        System.out.println(user1);
        System.out.println(user2);

    }

    /**
     * 创建方式一：空参构造
     */
    @Test
    public void test02(){
        //1.创建容器对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/create/applicationContext.xml");

        //2.向容器要对象
        Object user = applicationContext.getBean("user2");

        //3.打印user对象
        System.out.println(user instanceof User);
        System.out.println(user);

    }

    /**
     * 创建方式二：静态工厂
     */
    @Test
    public void test03(){
        //1.创建容器对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/create/applicationContext.xml");

        //2.向容器要对象
        Object user = applicationContext.getBean("user3");

        //3.打印user对象
        System.out.println(user instanceof User);
        System.out.println(user);

    }

    /**
     * 创建方式三：实例工厂
     */
    @Test
    public void test04(){
        //1.创建容器对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/create/applicationContext.xml");

        //2.向容器要对象
        Object user = applicationContext.getBean("user4");

        //3.打印user对象
        System.out.println(user instanceof User);
        System.out.println(user);

    }


    /**
     * scope:singleton, prototype
     */
    @Test
    public void test05(){
        //1.创建容器对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/create/applicationContext.xml");

        //2.向容器要对象
        Object user = applicationContext.getBean("user2");
        Object user2 = applicationContext.getBean("user2");
        Object user3 = applicationContext.getBean("user2");

        //3.打印user对象
        System.out.println(user instanceof User);
        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);
    }


    /**
     * 测试生命周期方法
     */
    @Test
    public void test06(){
        //1.创建容器对象
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/phoenix/create/applicationContext.xml");

        //2.向容器要对象
        Object user = applicationContext.getBean("user2");

        //3.打印user对象
        System.out.println(user instanceof User);
        System.out.println(user);
        applicationContext.close();
    }

}
