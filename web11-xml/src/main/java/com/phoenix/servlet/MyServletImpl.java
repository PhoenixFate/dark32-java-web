package com.phoenix.servlet;

public class MyServletImpl implements IMyServlet {

    static {
        System.out.println("this is myservlet implement");
    }

    MyServletImpl(){
        System.out.println("MyServlet implement()");
    }


    @Override
    public void init() {
        System.out.println("impl init");
    }

    @Override
    public void service() {
        System.out.println("impl service");
    }

    @Override
    public void destroy() {
        System.out.println("impl destroy");
    }
}
