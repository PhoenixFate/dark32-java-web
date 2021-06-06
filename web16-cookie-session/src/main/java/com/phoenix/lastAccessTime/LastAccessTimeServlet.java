package com.phoenix.lastAccessTime;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastAccessTimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //获得当前时间
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = simpleDateFormat.format(date);

        //1.创建cookie，记录当前最新的访问时间
        Cookie cookie=new Cookie("lastAccessTime",currentTime);
        cookie.setMaxAge(60*60*24*10);
        response.addCookie(cookie);

        //获得客户端携带的cookie
        //显示上次访问时间
        Cookie[] cookies = request.getCookies();
        if(cookie!=null){
            for(Cookie cookie1:cookies){
                if("lastAccessTime".equals(cookie1.getName())){
                    response.getWriter().write("您上次访问的时间： "+cookie1.getValue());
                }
            }
        }else{
            response.getWriter().write("欢迎您浏览本网页");
        }
    }
}
