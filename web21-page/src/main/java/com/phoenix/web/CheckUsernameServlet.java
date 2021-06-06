package com.phoenix.web;

import com.phoenix.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        //传递username到service层
        UserService userService=new UserService();
        boolean isExist=false;
        try {
            isExist= userService.checkUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().write("{\"isExist\":"+isExist+"}");

    }
}
