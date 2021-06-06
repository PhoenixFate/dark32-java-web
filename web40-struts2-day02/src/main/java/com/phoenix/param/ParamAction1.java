package com.phoenix.param;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Date;

/**
 * struts2 如果获得参数
 *
 * 属性驱动获得参数
 *
 * 每次请求action都会创建新的action对象，所以可以在类中申明成员变量
 * action是线程安全的
 */
public class ParamAction1 extends ActionSupport {

    public ParamAction1() {
        System.out.println("ParamAction 被创建了");
    }

    //第一种获得参数的方式：准备与参数名相同的属性名
    private String name;
    //自动类型转换 只能转换8大基本类型以及他们的包装类 + date
    private Integer age;
    private Date birthday;

    @Override
    public String execute() throws Exception {
        System.out.println(ActionContext.getContext().get("birthday"));
        System.out.println("name: "+name);
        System.out.println("age: "+age);
        System.out.println("birthday: "+birthday);
        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
