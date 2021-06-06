package com.phoenix.tag;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.phoenix.bean.User;

import java.util.ArrayList;
import java.util.List;

public class TagAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        System.out.println("tag action");
        List<String> list=new ArrayList<String>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        list.add("str4");

        ActionContext.getContext().put("list",list);

        User user=new User();
        user.setName("tom");
        ActionContext.getContext().getSession().put("user",user);

        return "success";
    }
}
