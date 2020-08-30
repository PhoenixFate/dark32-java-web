package com.phoenix.result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 转发
 */
public class ResultDemo1 extends ActionSupport {

    @Override
    public String execute() throws Exception {
        System.out.println("result demo1");

        return "success";
    }
}
