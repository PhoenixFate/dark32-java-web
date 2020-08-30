package com.phoenix.attribute;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();

        //向ServletContext域中存储数据
        servletContext.setAttribute("name","tom");

        //向ServletContext域中改数据
        servletContext.setAttribute("name","lucy");

        //从ServletContext域中删除数据
        servletContext.removeAttribute("name");
    }
}
