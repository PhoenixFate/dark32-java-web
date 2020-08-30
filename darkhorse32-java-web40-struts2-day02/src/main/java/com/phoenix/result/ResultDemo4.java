package com.phoenix.result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 重定向到action
 */
public class ResultDemo4 extends ActionSupport {

    @Override
    public String execute() throws Exception {
        System.out.println("result demo4");
        return "success";
    }
}
