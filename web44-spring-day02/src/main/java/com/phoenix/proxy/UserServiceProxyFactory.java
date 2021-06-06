package com.phoenix.proxy;

import com.phoenix.service.UserService;
import com.phoenix.service.UserServiceImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * aop原理
 * 动态代理
 * 动态代理针对接口
 */
public class UserServiceProxyFactory implements InvocationHandler {

    private UserService userService;

    public UserServiceProxyFactory(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserServiceProxy() {
        //生产动态代理
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(UserServiceProxyFactory.class.getClassLoader(), UserServiceImpl.class.getInterfaces(), this);
        return userServiceProxy;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("打开事务");
        Object invoke = method.invoke(userService, args);
        System.out.println("提交事务");
        return invoke;
    }
}
