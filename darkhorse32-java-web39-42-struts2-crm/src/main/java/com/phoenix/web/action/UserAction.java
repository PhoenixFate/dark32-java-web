package com.phoenix.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.phoenix.domain.User;
import com.phoenix.service.UserService;
import com.phoenix.service.impl.UserServiceImpl;

public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user=new User();

    private UserService userService=new UserServiceImpl();

    public String login() throws Exception {
        //1.调用service进行操作
        User loginUser = userService.login(user);
        //2.将返回的user放入session作为登陆成功的标示
        ActionContext.getContext().getSession().put("user",loginUser);
        //3.重定向到首页
        return "toHome";
    }

    public User getModel() {
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
