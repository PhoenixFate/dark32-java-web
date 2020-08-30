package com.phoenix.content;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public class ContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得某个参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.获得参数数组
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        for (String hobby:hobbies) {
            System.out.println("hobby:"+hobby);
        }
        //3.获取所有请求的参数名称
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            System.out.println(parameterNames.nextElement());
        }
        System.out.println("--------------------------------4----------------------------");
        //4.获取所有的参数，参数封装到Map<String,String[]>中
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String,String[]> entry:parameterMap.entrySet()){
            System.out.println(entry.getKey());
            for(String str:entry.getValue()){
                System.out.println(str);
            }
        }

        DispatcherType dispatcherType = request.getDispatcherType();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
