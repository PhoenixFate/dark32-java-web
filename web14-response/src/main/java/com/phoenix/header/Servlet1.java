package com.phoenix.header;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //重定向：
        //1.设置状态码为302
//        response.setStatus(302);
//        //2.设置响应头Location
//        response.setHeader("Location","/web14/servlet2");

        //上述两步封装为：
        //response.sendRedirect(url);
        response.sendRedirect(getServletContext().getContextPath()+"/servlet2");

    }
}
