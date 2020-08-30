package com.phoenix.valueStack;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.ValueStack;
import com.phoenix.bean.User;

/**
 * implements ModelDriven<User> 的原理
 */
public class ValueStackAction2 extends ActionSupport implements Preparable {

    private User user=new User();

    @Override
    public String execute() throws Exception {
        System.out.println(user);
        return "success";
    }

    public void prepare() throws Exception {
        //1.获得值栈
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        //2.将user压入栈顶
        valueStack.push(user);
    }
}
