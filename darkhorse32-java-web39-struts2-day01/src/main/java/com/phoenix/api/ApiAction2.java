package com.phoenix.api;

import com.opensymphony.xwork2.Action;

//方式2: 实现一个接口Action
// 里面有execute方法,提供action方法的规范.
// Action接口预置了一些字符串.可以在返回结果时使用.为了方便
public class ApiAction2 implements Action{

    public String execute() throws Exception {
        return null;
    }
}