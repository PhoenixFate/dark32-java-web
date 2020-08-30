package com.phoenix.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyTestServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("MyTestServlet initialized");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("MyTestServlet getServletConfig");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //向客户端提供响应方法
        System.out.println("MyTestServlet service");
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
        httpServletResponse.getWriter().write("this is my first servlet");
    }

    @Override
    public String getServletInfo() {
        System.out.println("MyTestServlet getServletInfo");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("MyTestServlet destroyed");
    }
}
