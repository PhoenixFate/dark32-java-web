package com.phoenix.servlet;

import org.junit.Test;

public class TestMyServlet {

    @Test
    public void test1(){
        String servletImpl="com.phoenix.servlet.MyServletImpl";

        try {
            Class<?> aClass = Class.forName(servletImpl);
            MyServletImpl myServlet=(MyServletImpl) aClass.newInstance();
            myServlet.init();
            myServlet.service();
            myServlet.destroy();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
