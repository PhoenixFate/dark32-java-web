package com.phoenix.test;

import com.phoenix.domain.User;

public class MyTest {

    protected int age;
    protected String name;

    MyTest(int age,String name){
        this.age=age;
        this.name=name;
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        int i=1;
        int j=1;
        System.out.println("a: "+System.identityHashCode(a));
        System.out.println("b: "+System.identityHashCode(b));
        System.out.println("i: "+System.identityHashCode(i));
        System.out.println("j: "+System.identityHashCode(j));
        System.out.println("1: "+System.identityHashCode(1));
        User user=new User();
        System.out.println("user: "+System.identityHashCode(user));
        String s = test01(a, user);
        System.out.println("return hello address:"+System.identityHashCode(s));
        System.out.println(System.identityHashCode(user));
        System.out.println(user.getUserName());

        User user2 = test02();
        System.out.println("main user2 address:"+System.identityHashCode(user2));

        System.out.println(TestEnum.ABC);
        System.out.println(TestEnum.GGG);
    }

    public static String test01(Integer a,User user) {
        System.out.println("test a: "+System.identityHashCode(a));
        System.out.println("test user: "+System.identityHashCode(user));
        user.setUserName("tom");
        String hello="hello";
        System.out.println("test hello address: "+System.identityHashCode(hello));
        return hello;
    }


    public static User test02(){
        User user=new User();
        System.out.println("test02 user address:"+System.identityHashCode(user));
        return user;
    }

}
