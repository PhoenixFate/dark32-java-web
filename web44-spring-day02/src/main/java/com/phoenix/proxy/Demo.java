package com.phoenix.proxy;

import com.phoenix.service.UserService;
import com.phoenix.service.UserServiceImpl;
import org.junit.Test;

public class Demo {

    /**
     * 动态代理测试
     */
    @Test
    public void fun1() {
        UserService userService = new UserServiceImpl();
        UserServiceProxyFactory userServiceProxyFactory = new UserServiceProxyFactory(userService);
        UserService userServiceProxy = userServiceProxyFactory.getUserServiceProxy();
        userServiceProxy.save();
        //代理对象和被代理对象实现了相同的接口，并没有继承关系
        System.out.println(userServiceProxy instanceof UserServiceImpl);
    }

    /**
     * cglib代理测试
     */
    @Test
    public void fun2() {
        UserServiceProxyFactory2 userServiceProxyFactory2 = new UserServiceProxyFactory2();
        UserService userServiceProxy = userServiceProxyFactory2.getUserServiceProxy();
        userServiceProxy.save();
        //判断代理对象是否属于被代理对象类型
        System.out.println(userServiceProxy instanceof UserServiceImpl);
    }

}
