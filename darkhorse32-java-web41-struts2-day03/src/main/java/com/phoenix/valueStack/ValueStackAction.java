package com.phoenix.valueStack;

import com.opensymphony.xwork2.ActionSupport;

public class ValueStackAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        System.out.println("value stack action");
        return "success";
    }
}
