package com.phoenix.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建cookie对象
        Cookie cookie=new Cookie("name","lisi-aa");
        //1.1为cookie对象设置持久化时间----cookie信息在硬盘上保存的时间
        cookie.setMaxAge(60*10);//单位为秒      //10min
        //1.2为cookie设置携带的路径
//        cookie.setPath("/web16/sendCookie");//访问sendCookie资源时才携带这个cookie
//        cookie.setPath(request.getContextPath());//访问该工程下面任何资源时都携带这个cookie
        cookie.setPath("/");//访问服务器下面所有都资源都携带这个cookie（包括访问其他项目）

        //如果不设置携带路径，那么该cookie信息 会在访问产生该cookie的web资源所在的路径都携带cookie信息
        //例如：在/web16/demo/sendCookie 下产生cookie则访问/web16/demo/下所有资源都携带cookie

        //2.将cookie中存储的信息发送到客户端
        response.addCookie(cookie);

    }
}


