package com.phoenix.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 拦截器：第一种创建方式
 *
 * 拦截器到生命周期：随项目的启动而创建，随项目的关闭而销毁
 */
public class MyInterceptor1 implements Interceptor {

    public void destroy() {

    }

    /**
     *
     */
    public void init() {

    }

    /**
     * 核心到拦截方法
     * @param actionInvocation
     * @return
     * @throws Exception
     */
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        return null;
    }
}
