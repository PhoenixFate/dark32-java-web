package com.phoenix.web;

import com.phoenix.domain.Product;
import com.phoenix.service.ProductService2;
import com.phoenix.vo.PageBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProductListServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currentPageStr = req.getParameter("currentPage");
        String pageCountStr=req.getParameter("currentCount");

        Integer currentPage=null;
        if(currentPageStr==null){
            currentPage=1;
        }else {
            currentPage=Integer.parseInt(currentPageStr);
        }
        Integer pageCount=null;
        if(pageCountStr!=null){
            pageCount= Integer.parseInt(pageCountStr);
        }else {
            pageCount=12;
        }

        System.out.println("currentPage: --------------"+currentPage);
        System.out.println("pageCount: ---------------------------"+pageCount);
        ProductService2 productService2=new ProductService2();
        try {
            PageBean<Product> pageBean = productService2.findPageBean(currentPage, pageCount);
            System.out.println(pageBean);
            req.setAttribute("pageBean",pageBean);
            req.getRequestDispatcher("/product_list2.jsp").forward(req,resp);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //super.doPost(req, resp);
    }
}
