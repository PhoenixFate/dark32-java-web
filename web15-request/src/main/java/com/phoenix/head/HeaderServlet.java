package com.phoenix.head;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class HeaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得指定的头
        String userAgent = request.getHeader("User-Agent");
        System.out.println("User-Agent:"+userAgent);
        System.out.println("---------------------------------------------------------");

        //2.获得所有请求头的名字
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            System.out.print(name+":");
            System.out.println(request.getHeader(name));
        }

        response.getWriter().write("HeaderServlet");


    }
}
