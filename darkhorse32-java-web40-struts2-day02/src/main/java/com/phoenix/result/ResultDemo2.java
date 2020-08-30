package com.phoenix.result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 重定向
 */
public class ResultDemo2 extends ActionSupport {

    @Override
    public String execute() throws Exception {


        return "success";
    }
}
