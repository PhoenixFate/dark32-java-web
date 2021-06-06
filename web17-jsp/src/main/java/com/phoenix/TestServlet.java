package com.phoenix;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().print("1 hello world ");
        resp.getWriter().print("2 hello world <br> ");
        resp.getWriter().print("3 hello world \n ");
        resp.getWriter().println("4 hello world ");
        resp.getWriter().println("5 hello world <br> ");
        resp.getWriter().println("6 hello world \n ");


        resp.getWriter().write("7 hello world ");
        resp.getWriter().write("8 hello world <br> ");
        resp.getWriter().write("9 hello world \n ");



        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }
}
