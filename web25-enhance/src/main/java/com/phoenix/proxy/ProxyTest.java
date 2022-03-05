package com.phoenix.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    @Test
    public void test1() {
        //获得动态代理对象----- 在运行时 在内存中动态为Target创建一个虚拟的代理对象
        //proxyInstance是代理对象 根据参数确定到底是谁的代理对象
        //loader：与目标对象相同的类加载器
        TargetInterface proxyInstance = (TargetInterface)Proxy.newProxyInstance(Target.class.getClassLoader(), new Class[]{TargetInterface.class}, new InvocationHandler() {
            //invoke 代表的是执行代理对象的方法
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //method：代表目标对象的方法字节码对象
                //args: 代表目标对象的响应的方法的参数
                System.out.println("增强前");
                //执行目标对象方法
                Object invoke = method.invoke(new Target(), args);
                System.out.println("增强后");
                return invoke;
            }
        });
        proxyInstance.method1();
        String s = proxyInstance.method2();
        System.out.println(s);
    }

    @Test
    public void test2(){
        Target target=new Target();
        //注意：JDK的Proxy方式实现的动态代理 目标对象必须有接口 没有接口不能实现jdk版的动态代理
        TargetInterface targetInterface = (TargetInterface)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //反射知识点
                Object invoke = method.invoke(target, args);
                //return返回的值是给代理对象
                return invoke;
            }
        });
        targetInterface.method1();
        String s = targetInterface.method2();
        System.out.println(s);
    }




}
