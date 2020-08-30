package com.phoenix.web;

import com.phoenix.domain.Product;
import com.phoenix.service.ReviewProductService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReviewProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReviewProductService reviewProductService=new ReviewProductService();
        try {
            List<Product> allProductList = reviewProductService.getAllProductList();
            req.setAttribute("productList",allProductList);
            req.getRequestDispatcher("/admin/product2/list.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
    }
}
