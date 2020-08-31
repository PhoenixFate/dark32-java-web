package com.phoenix.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.phoenix.domain.User;
import com.phoenix.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    private UserService userService;

    private User user=new User();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String login() throws Exception {
        //调用service，执行登录逻辑
        User user = userService.getUserByCodePassword(this.user);
        //将返回的user放入session域
        ActionContext.getContext().getSession().put("user",user);
        //重定向到项目首页
        return "toHome";
    }

    public User getModel() {
        return user;
    }
}
