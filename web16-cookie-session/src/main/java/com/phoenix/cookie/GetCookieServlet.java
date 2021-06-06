package com.phoenix.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        //通过cookie名称获得想要的cookie
        if(cookies!=null){
            for(Cookie cookie:cookies){
                String cookieName=cookie.getName();
                response.getWriter().write("cookie name: "+cookieName  +"   <br>");
                if("name".equals(cookieName)){
                    String cookieValue=cookie.getValue();
                    response.getWriter().write("the 'name' cookieValue: "+cookieValue + " <br>");
                }
                response.getWriter().write("cookie value: "+cookie.getValue() +" <br>");
            }
        }

    }
}
