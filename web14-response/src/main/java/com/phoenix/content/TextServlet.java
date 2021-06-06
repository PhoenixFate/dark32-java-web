package com.phoenix.content;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //1.设置response查询的码表
//        response.setCharacterEncoding("UTF-8");
//        //2.通过一个头Content-Type，告知客户端使用utf-8解码(实际中，设置完这句之后，自动设置第一句）
//        response.setHeader(" -Type","text/html;charset=UTF-8");

        //response.setHeader("Content-Type","text/html;charset=UTF-8") 的简写方式：
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().write("english<br/>");
        response.getWriter().write("中文测试");
    }
}
