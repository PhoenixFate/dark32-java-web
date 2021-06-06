package com.phoenix.web;

import com.phoenix.domain.AdminUser;
import com.phoenix.service.AdminLoginService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String adminUsername = req.getParameter("adminUsername");
        String adminPassword=req.getParameter("adminPassword");
        AdminLoginService adminLoginService=new AdminLoginService();
        AdminUser user=null;
        try {
            user = adminLoginService.login(adminUsername, adminPassword);
            if(user==null){
                req.setAttribute("loginMessage","用户名或者密码错误");
                req.getRequestDispatcher("admin/index.jsp").forward(req,resp);
            }else {
                req.getSession().setAttribute("adminUser",user);
                resp.sendRedirect("admin/home.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
