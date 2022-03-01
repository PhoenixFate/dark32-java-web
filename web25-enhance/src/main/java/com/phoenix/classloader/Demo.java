package com.phoenix.classloader;

import java.net.URL;

public class Demo {

    public static void main(String[] args) {
        //获得Demo字节码文件的类加载器
        Class<Demo> tempClass=Demo.class;//获得Demo的字节码对象
        ClassLoader classLoader=tempClass.getClassLoader();//获得类加载器
        //getResource的参数路径相对classes(src)
        URL resource = classLoader.getResource("com/phoenix/b.properties");//获得classPath下的任何的资源
        System.out.println(resource.getPath());
        System.out.println("xxx");
    }
}
