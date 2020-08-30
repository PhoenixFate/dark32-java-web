package com.phoenix.line;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取客户端请求方式：
        String method = request.getMethod();
        System.out.println("method:" + method);

        //2、获得请求的资源相关的内容
        String requestURI = request.getRequestURI();//各种地址，此处为相对地址；
        System.out.println("uri:" + requestURI);
        StringBuffer requestURL = request.getRequestURL();//url：网络地址
        System.out.println("url:" + requestURL);

        //获取web应用名称：
        String contextPath = request.getContextPath();
        System.out.println("contextPath:" + contextPath);

        //get提交url之后的参数字符串a=b&c=d
        String queryString = request.getQueryString();
        System.out.println("queryString:" + queryString);

        //3.获得客户机的信息
        //获取地址
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr:" + remoteAddr);


    }
}
