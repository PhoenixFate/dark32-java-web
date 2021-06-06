package com.phoenix.web;

import com.phoenix.service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AdminDeleteProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取要删除数据的pid
        String pid=request.getParameter("pid");
        AdminProductService adminProductService=new AdminProductService();
        try {
            adminProductService.deleteProductByPid(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //request.getRequestDispatcher("adminProductList").forward(request,response);
        response.sendRedirect(request.getContextPath()+"/adminProductList");
    }
}
