package com.phoenix.proxy;

import com.phoenix.service.UserService;
import com.phoenix.service.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * aop原理
 * cglib代理
 * spring默认整合龙cglib包，所以不需要导包
 * cglib代理通过继承实现代理
 */
public class UserServiceProxyFactory2 implements MethodInterceptor {


    public UserService getUserServiceProxy() {
        //帮为名生成代理对象
        Enhancer enhancer = new Enhancer();
        //设置对谁进行代理
        enhancer.setSuperclass(UserServiceImpl.class);
        //代理要做什么
        enhancer.setCallback(this);
        //创建代理对象
        UserService userService = (UserService) enhancer.create();
        return userService;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("打开事务");
        //调用原有方法
        Object returnValue = methodProxy.invokeSuper(o, args);
        System.out.println("提交事务");
        return returnValue;
    }
}
