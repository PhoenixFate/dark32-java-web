package com.phoenix.servlet;

import javax.servlet.*;
import java.io.IOException;

public class QuickStartServlet implements Servlet {

    //默认第一次访问servlet时，创建该对象
    //创建对象时，调用init方法
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("quickstart servlet initialization start");
        //servletConfig：代表的是该servlet对象的配置信息
        //1.获取servelt的name
        System.out.println("name:"+servletConfig.getServletName());
        //2.获得servlet初始化参数
        System.out.println(servletConfig.getInitParameter("url"));
        //3.获得servletContext对象
        ServletContext servletContext=servletConfig.getServletContext();
        System.out.println("quickstart servlet initialization end");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("quick-start servlet is running");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    //服务器关闭时，servlet就销毁了
    //servlet销毁前调用destroy方法
    @Override
    public void destroy() {
        System.out.println("quickstart servlet is destroied");
    }
}
