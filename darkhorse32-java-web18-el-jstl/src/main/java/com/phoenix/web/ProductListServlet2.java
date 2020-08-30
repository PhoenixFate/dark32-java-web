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

public class ProductListServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService=new ProductService();
        List<Product> allProduct = null;
        try {
            allProduct = productService.findAllProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("allProducts",allProduct);
        req.getRequestDispatcher("/product_list2.jsp").forward(req,resp);
        // super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       // super.doPost(req, resp);
    }
}
