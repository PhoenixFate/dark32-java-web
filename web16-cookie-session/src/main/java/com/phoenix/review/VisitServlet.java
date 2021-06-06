package com.phoenix.review;

import com.phoenix.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = req.getCookies();
        StringBuffer stringBuffer=new StringBuffer();
        for(Cookie cookie:cookies){
//            System.out.println(cookie);
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
            System.out.println(cookie.getPath());
            System.out.println("-------------");
            stringBuffer.append("name : "+cookie.getName()+"  value : "+cookie.getValue()+"<br>");
        }


//        Cookie cookie=new Cookie("test","test123");
//        cookie.setMaxAge(60*60);// 单位秒 60*60：1小时
//        resp.addCookie(cookie);

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);

        Cookie lastVisitTime =new Cookie("lastVisitTime",URLEncoder.encode(format,"utf-8"));
        lastVisitTime.setMaxAge(Integer.MAX_VALUE);
        resp.addCookie(lastVisitTime);


        resp.getWriter().write(stringBuffer.toString());
        resp.getWriter().println("");

        Cookie lastVisitTimeCookie = CookieUtil.findCookie(cookies, "lastVisitTime");
        if(lastVisitTimeCookie!=null){
            resp.getWriter().println("您上次 登陆的时间 ： "+ URLDecoder.decode(lastVisitTimeCookie.getValue(),"utf-8"));
        }else {
            resp.getWriter().println("欢迎初次访问！");
        }




        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }
}
