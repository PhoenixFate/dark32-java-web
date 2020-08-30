package com.phoenix.review;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("---------------------request header----------------------");
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String  nextElement=headerNames.nextElement();
            System.out.print(nextElement);
            System.out.println(" : "+req.getHeader(nextElement));
            resp.getWriter().print(nextElement);
            resp.getWriter().println(" : "+req.getHeader(nextElement));
        }


        System.out.println("content type: "+req.getContentType());
        System.out.println("content length: " +req.getContentLength());
        System.out.println("character encoding: "+req.getCharacterEncoding());


        resp.getWriter().println("content type: "+req.getContentType());
        resp.getWriter().println("content lenght: "+req.getContentLength());
        resp.getWriter().println("character encoding: "+req.getCharacterEncoding());
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }
}
