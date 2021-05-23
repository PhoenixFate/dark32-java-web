package com.phoenix.shop.web.servlet;

import com.phoenix.shop.domain.AdminUser;
import com.phoenix.shop.service.AdminUserService;
import com.phoenix.shop.web.base.BaseServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AdminUserServlet extends BaseServlet {

    private AdminUserService adminUserService=new AdminUserService();

    public void loginUI(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            resp.sendRedirect(req.getContextPath()+"/admin/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest req, HttpServletResponse resp)  {
        HttpSession session = req.getSession();
        try {
            String checkCode = req.getParameter("checkCode");
            String sessionCheckCode = (String) session.getAttribute("sessionCheckCode");
            if(!sessionCheckCode.equals(checkCode)){
                req.setAttribute("loginInfo","验证码错误");
                req.getRequestDispatcher("/admin/index.jsp").forward(req,resp);
            }
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            AdminUser adminUser = adminUserService.login(username, password);
            if(adminUser!=null){
                session.setAttribute("adminUser",adminUser);
                resp.sendRedirect(req.getContextPath()+"/admin/home.jsp");
            }else {
                req.setAttribute("loginInfo","用户名或者密码错误");
                req.getRequestDispatcher("/admin/index.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp)  {
        HttpSession session = req.getSession();
        session.removeAttribute("adminUser");
        try {
            resp.sendRedirect(req.getContextPath()+"/admin/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
