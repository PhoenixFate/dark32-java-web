package com.phoenix.bean;

public class UserFactory {

    public static User createUser() {
        System.out.println("静态工厂创建user");
        return new User();
    }

    public UserFactory() {
        System.out.println("UserFactory()");
    }

    public User createUser2() {
        System.out.println("实例工厂创建user2");
        return new User();
    }
}
