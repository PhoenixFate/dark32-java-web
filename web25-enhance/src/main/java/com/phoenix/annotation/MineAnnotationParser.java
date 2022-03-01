package com.phoenix.annotation;

import java.lang.reflect.Method;

public class MineAnnotationParser {

    public static void main(String[] args) throws NoSuchMethodException {
        //解析show方法上面的@MineAnnotation的参数
        Class<AnnotationTest> temp=AnnotationTest.class;
        Method show = temp.getMethod("show");
        //获得show方法上面的@MineAnnotation注解
        MineAnnotation annotation = show.getAnnotation(MineAnnotation.class);
        //获得注解上面的属性值
        System.out.println(annotation.name());
        System.out.println(annotation.age());
        System.out.println(annotation.sex());
    }

}
