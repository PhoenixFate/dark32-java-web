package com.phoenix.review;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestLineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("method: "+req.getMethod());
        System.out.println("request uri: "+req.getRequestURI());
        System.out.println("query string: "+req.getQueryString());
        System.out.println("protocol: "+req.getProtocol());
        System.out.println("context path: "+req.getContextPath());
        System.out.println("servlet path: "+req.getServletPath());
        System.out.println("remote address: "+req.getRemoteAddr());
        System.out.println("remote host: "+req.getRemoteHost());
        System.out.println("remote port: "+req.getRemotePort());
        System.out.println("local address: "+req.getLocalAddr());
        System.out.println("local name: "+req.getLocalName());
        System.out.println("local port: "+req.getLocalPort());
        System.out.println("server name: "+req.getServerName());
        System.out.println("server port: "+req.getServerPort());
        System.out.println("schema: "+req.getScheme());
        System.out.println("url: "+req.getRequestURL());
        //super.doGet(req, resp);


        resp.getWriter().println("method: "+req.getMethod());
        resp.getWriter().println("request uri: "+req.getRequestURI());
        resp.getWriter().println("query string: "+req.getQueryString());
        resp.getWriter().println("protocol: "+req.getProtocol());
        resp.getWriter().println("context path: "+req.getContextPath());
        resp.getWriter().println("servlet path: "+req.getServletPath());
        resp.getWriter().println("remote address: "+req.getRemoteAddr());
        resp.getWriter().println("remote host: "+req.getRemoteHost());
        resp.getWriter().println("remote port: "+req.getRemotePort());
        resp.getWriter().println("local address: "+req.getLocalAddr());
        resp.getWriter().println("local name: "+req.getLocalName());
        resp.getWriter().println("local port"+req.getLocalPort());
        resp.getWriter().println("server name: "+req.getServerName());
        resp.getWriter().println("server port: "+req.getServerPort());
        resp.getWriter().println("schema: "+req.getScheme());
        resp.getWriter().println("url: "+req.getRequestURL());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }
}
