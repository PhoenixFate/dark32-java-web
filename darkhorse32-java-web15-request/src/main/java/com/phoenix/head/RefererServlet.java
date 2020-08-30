package com.phoenix.head;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RefererServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //refer头的作用：执行该资源访问的来源
        //做防盗链
        //对该新闻的来源进行判断
        String referer = request.getHeader("referer");
        System.out.println("referer:"+referer);
        //判断此新闻是本网站点击跳转过去的
        if(referer!=null&&referer.startsWith("http://localhost")){
            //是从我自己的网站跳转过去的 可以访问
            response.getWriter().write("successfully! <br>");
        }else{
            response.getWriter().write("你是盗墓贼，可耻!");
            //response.sendRedirect("/web15/form.html");
        }

        response.getWriter().write("refererServlet 中文");
    }
}
