package com.phoenix;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //动态的响应html页面
        writer.write("<!DOCTYPE html>\n");
        writer.write("<html>\n");
        writer.write("<head>\n");
        writer.write("<meta charset='UTF-8'>\n");
        writer.write("<title>Insert title here</title>\n");
        writer.write("</head>\n");
        writer.write("<body>\n");
        writer.write("<h1>这个页面很繁琐</h1>\n");
        writer.write("<h2>这个页面很繁琐2</h2>\n");
        writer.write("<h3>这个页面很繁琐3</h3>\n");
        writer.write("<h4>这个页面很繁琐4</h4>\n");
        writer.write("</body>\n");
        writer.write("</html>\n");
    }
}
