package com.phoenix.param;

import com.opensymphony.xwork2.ActionSupport;
import com.phoenix.domain.User;

/**
 * struts2 如果获得参数
 *
 * 第二种：对象驱动
 *
 */
public class ParamAction2 extends ActionSupport {

    private User user;

    @Override
    public String execute() throws Exception {
        System.out.println("param action2");
        System.out.println("user: "+user);
        return "success";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
