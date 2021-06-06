package com.phoenix.domain;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

public class Customer implements HttpSessionActivationListener, Serializable {

    private static final long serialVersionUID = 1L;

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
    //钝化
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("customer 在session中被钝化");
    }

    @Override
    //活化
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.println("customer 在session中被活化");
    }
}
