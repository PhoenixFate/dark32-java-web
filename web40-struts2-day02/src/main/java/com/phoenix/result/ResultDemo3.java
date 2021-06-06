package com.phoenix.result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 转发到action
 */
public class ResultDemo3 extends ActionSupport {

    @Override
    public String execute() throws Exception {
        System.out.println("result demo3");
        return "success";
    }
}
