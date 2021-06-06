package com.phoenix.servlet;

import com.phoenix.entity.User;
import com.phoenix.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("count",0);
        String driver = servletContext.getInitParameter("driver");
        System.out.println(driver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password =req.getParameter("password");
        UserService userService=new UserService();
        try {
            User user = userService.login(username, password);
            resp.setContentType("text/html;charset=utf-8");
            if(user==null){
                resp.getWriter().println("登陆失败");
                resp.getWriter().println("aaaa");
            }else {
                ServletContext servletContext = getServletContext();
                Integer count = (Integer)servletContext.getAttribute("count");
                count++;
                resp.getWriter().println("登陆成功; count= "+count);
                resp.getWriter().println("bbbb");
                servletContext.setAttribute("count",count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //super.doPost(req, resp);
    }
}
