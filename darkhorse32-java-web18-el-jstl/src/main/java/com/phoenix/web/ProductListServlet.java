package com.phoenix.web;

import com.phoenix.domain.Product;
import com.phoenix.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //传递请求到service层
        ProductService productService=new ProductService();
        List<Product> productList=null;
        try {
            productList = productService.findAllProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //将数据保存至request域
        request.setAttribute("productList",productList);
        //全部商品准备好了，转发给进行展示
        request.getRequestDispatcher("/product_list.jsp").forward(request,response);


    }
}
