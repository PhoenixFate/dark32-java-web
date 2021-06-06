package com.phoenix.test;

public class MyTest2 extends MyTest {

    MyTest2(int age,String name){
        super(age,name);

    }

    public static void main(String[] args) {
        test02();
        MyTest2 myTest2=new MyTest2(10,"tom");
        System.out.println(myTest2.age);
        System.out.println(myTest2.name);
    }


}
