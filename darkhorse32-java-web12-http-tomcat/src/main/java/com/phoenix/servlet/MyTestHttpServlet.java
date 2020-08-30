package com.phoenix.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class  MyTestHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        ServletContext servletContext = getServletContext();
        System.out.println(servletContext);
        ServletContext url = servletContext.getContext("url");
        System.out.println(url);
        resp.getWriter().write("this is my second servlet (Get)");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        resp.getWriter().write("this is my second servlet (Post)");
    }
}
