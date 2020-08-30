package com.phoenix.valueStack;

import com.opensymphony.xwork2.ActionSupport;

public class ValueStackAction4 extends ActionSupport{

    private String name;

    @Override
    public String execute() throws Exception {
        name="aaa";
        System.out.println("value stack action4 ");
        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
