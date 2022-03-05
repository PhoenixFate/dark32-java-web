package com.phoenix.proxy;

public class Target implements TargetInterface {
    @Override
    public void method1() {
        System.out.println("target method");
    }

    @Override
    public String method2() {
        System.out.println("target method2");
        return "success";
    }
}
