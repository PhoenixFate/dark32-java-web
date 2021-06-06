package com.phoenix.api;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

/**
 * 在action中访问 servlet中的原生api
 *
 * 方式一：通过ActionContext，常用这种方式
 */
public class ApiDemo1 extends ActionSupport {

    @Override
    public String execute() throws Exception {
        //request域 (struts2并不推荐使用原生的request)
        Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
        //struts2推荐使用ActionContext.getContext()作为request域
        ActionContext.getContext().put("name","requestScope");
        System.out.println(ActionContext.getContext().get("test"));


        //session域
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.put("name","sessionScope");
        //application域
        Map<String, Object> application = ActionContext.getContext().getApplication();
        application.put("name","applicationScope");

        return super.execute();
    }



}
