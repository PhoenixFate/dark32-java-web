package com.phoenix.web;

import com.phoenix.domain.Product;
import com.phoenix.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProductInfoServlet extends HttpServlet {

    private ProductService productService=new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //传递请求到service层
        String pid = request.getParameter("pid");
        Product productDetail=null;
        try {
            productDetail= productService.getInfo(pid);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("productInfo",productDetail);
        request.getRequestDispatcher("/product_info.jsp").forward(request,response);
    }
}
