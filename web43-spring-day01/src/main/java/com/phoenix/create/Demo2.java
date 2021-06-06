package com.phoenix.create;

import com.phoenix.bean.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo2 {

    private ApplicationContext applicationContext;

    @Before
    public void createContext() {
        applicationContext = new ClassPathXmlApplicationContext("com/phoenix/create/applicationContext.xml");
    }

    /**
     * 创建方式1:空参构造
     */
    @Test
    public void fun1() {
        //测试创建对象方式1
        User user1 = (User) applicationContext.getBean("user1");
        System.out.println(user1);
    }

    /**
     * 创建方式2:静态工厂
     */
    @Test
    public void fun2() {
        //2 向容器"要"user对象
        User u = (User) applicationContext.getBean("user2");
        //3 打印user对象
        System.out.println(u);
    }

    /**
     * 创建方式3:实例工厂
     */
    @Test
    public void fun3() {
        //2 向容器"要"user对象
        User u = (User) applicationContext.getBean("user3");
        //3 打印user对象
        System.out.println(u);
    }

    @Test
    //scope:singleton 单例
    //scope:prototype 多例
    public void fun4() {
        //2 向容器"要"user对象
        User u1 = (User) applicationContext.getBean("user1");
        User u2 = (User) applicationContext.getBean("user1");
        User u3 = (User) applicationContext.getBean("user1");
        User u4 = (User) applicationContext.getBean("user1");
        System.out.println(u2 == u4);//单例:true
        //多例:false
        //3 打印user对象
        System.out.println(u1);
    }

    @Test
    //测试生命周期方法
    public void fun5(){
        //1 创建容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("com/phoenix/create/applicationContext.xml");
        //2 向容器"要"user对象
        User u = (User) ac.getBean("user1");
        //3 打印user对象
        System.out.println(u);
        //关闭容器,触发销毁方法
        ac.close();
    }
}
