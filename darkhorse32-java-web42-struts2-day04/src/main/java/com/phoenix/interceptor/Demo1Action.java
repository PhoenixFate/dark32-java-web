package com.phoenix.interceptor;

import com.opensymphony.xwork2.ActionSupport;

public class Demo1Action extends ActionSupport {

    public String add() throws Exception {
        System.out.println("demo1 add");
        return "success";
    }

    public String delete() throws Exception {
        System.out.println("demo1 delete");
        return "success";
    }

    public String update() throws Exception {
        System.out.println("demo1 update");
        return "success";
    }

    public String query() throws Exception {
        System.out.println("demo1 query");
        return "success";
    }
}
