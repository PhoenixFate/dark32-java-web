package com.phoenix.annotation;

import org.junit.Test;

public class TestDemo {

    @Test
    public void test1(){
        System.out.println("test");
    }

    @MyTest
    public void test2(){
        System.out.println("test2");
    }


}
