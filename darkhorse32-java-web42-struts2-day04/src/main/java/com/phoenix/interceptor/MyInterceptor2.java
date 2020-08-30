package com.phoenix.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 第二种创建拦截器的方式
 */
public class MyInterceptor2 extends AbstractInterceptor {

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        return null;
    }
}
