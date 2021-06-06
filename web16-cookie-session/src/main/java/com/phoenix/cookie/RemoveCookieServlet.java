package com.phoenix.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除cookie的方法：
        Cookie cookie=new Cookie("name","");
        cookie.setPath("/web16");//路径需要一致
        //maxAge=0 即为删除cookie
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
