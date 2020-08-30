package com.phoenix.api;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 在action中访问 servlet中的原生api
 *
 * 方式二：通过ServletActionContext
 */
public class ApiDemo2 extends ActionSupport {

    @Override
    public String execute() throws Exception {
        //原生request
        HttpServletRequest request = ServletActionContext.getRequest();

        //原生response
        HttpServletResponse response = ServletActionContext.getResponse();

        //原生session
        HttpSession session = ServletActionContext.getRequest().getSession();

        //原生servletContext
        ServletContext servletContext = ServletActionContext.getServletContext();

        return super.execute();
    }



}
