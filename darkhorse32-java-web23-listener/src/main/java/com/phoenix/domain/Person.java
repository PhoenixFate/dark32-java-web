package com.phoenix.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Person implements HttpSessionBindingListener {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    //绑定的方法
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Person 被绑定");
    }

    @Override
    //解绑的方法
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("Person 被解绑了");
    }
}
