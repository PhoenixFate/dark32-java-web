package com.phoenix.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 第三种拦截器的创建方式
 *
 * 继承 MethodFilterInterceptor
 * 功能：定制拦截器拦截的方法
 *      定制哪些方法需要定制
 *      定制哪些方法不需要定制
 *
 *
 */
public class MyInterceptor3 extends MethodFilterInterceptor {

    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //过滤器 前处理
        System.out.println("interceptor3 前处理");
        //放行
        String invoke = actionInvocation.invoke();
        //过滤器 后处理
        System.out.println("interceptor3 后处理");
        //不放行，return才有意义
        return "home";
    }

}
