package com.phoenix.hello;

import com.phoenix.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

    @Test
    public void fun1() {
        //1. 创建容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2. 向容器要user对象
        User user = (User)applicationContext.getBean("user");
        //3. 打印User对象
        System.out.println(user);
    }

}
