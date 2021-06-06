package com.phoenix.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import java.util.Map;

public class LoginInterceptor extends MethodFilterInterceptor {

    //除登陆以外，其他方法都拦截

    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //1.获得session
        Map<String, Object> session = ActionContext.getContext().getSession();
        //2.获得登陆标示
        Object user = session.get("user");
        //3.判断登陆标示是否存在
        if(user==null){
            // 3.1不存在，重定向到登陆页面
            return "toLogin";
        }else {
            // 3.2存在，放行
            actionInvocation.invoke();
        }
        return null;
    }
}
