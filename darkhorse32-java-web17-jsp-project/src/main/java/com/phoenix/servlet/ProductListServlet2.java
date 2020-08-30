package com.phoenix.servlet;

import com.phoenix.domain.Product;
import com.phoenix.service.ProductService;
import com.phoenix.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductListServlet2 extends HttpServlet {

    private ProductService productService=new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> allProducts = productService.getAllProducts();
            req.setAttribute("productList2",allProducts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/product_list2.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
    }
}
