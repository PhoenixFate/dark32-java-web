package com.phoenix.web;

import com.phoenix.domain.Category;
import com.phoenix.domain.Product;
import com.phoenix.service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminProductListServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.传递请求
        AdminProductService adminProductService=new AdminProductService();
        List<Product> productList = null;
        try {
            productList = adminProductService.findAllProduct();
            //System.out.println(productList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将数据放入request域
        request.setAttribute("productList",productList);

        //获得所有商品分类的数据
        List<Category> categoryList= null;
        try {
            categoryList = adminProductService.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList",categoryList);

        request.getRequestDispatcher("/admin/product/list.jsp").forward(request,response);


    }
}
