package com.phoenix.tag;

import com.opensymphony.xwork2.ActionSupport;

public class TagAction2 extends ActionSupport {


    @Override
    public String execute() throws Exception {
        System.out.println("tag action2");
        return "success";
    }
}
