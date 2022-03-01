package com.phoenix.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTestParser {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {

        Class<TestDemo> testDemoClass = TestDemo.class;
        Method[] methods = testDemoClass.getMethods();
        //获得使用了注解@MyTest的方法
        for(Method method:methods){
            //判断该方法是否使用了@MyTest注解
            boolean annotationPresent = method.isAnnotationPresent(MyTest.class);
            if(annotationPresent){
                //有@MyTest注解
                method.invoke(testDemoClass.newInstance());
            }
        }
    }

}
