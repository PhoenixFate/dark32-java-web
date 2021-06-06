package com.phoenix.web;

import com.phoenix.domain.Category;
import com.phoenix.service.AdminProductService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminAddProductUIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得所有的商品分类数据
        AdminProductService adminProductService=new AdminProductService();
        List<Category> categoryList= null;
        try {
            categoryList = adminProductService.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(categoryList);
        request.setAttribute("categoryList",categoryList);
        request.getRequestDispatcher("admin/product/add.jsp").forward(request,response);

    }
}
