package com.phoenix.param;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.phoenix.domain.User;

public class ParamAction3 extends ActionSupport implements ModelDriven<User> {

    private User user=new User();

    @Override
    public String execute() throws Exception {
        System.out.println("param action3");
        System.out.println(user);
        return "success";
    }

    public User getModel() {
        return user;
    }
}
