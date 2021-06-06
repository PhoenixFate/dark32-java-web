package com.phoenix.web;

import com.phoenix.domain.Product;
import com.phoenix.service.ProductService;
import com.phoenix.vo.PageBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有商品的列表
        //1.
        ProductService productService=new ProductService();
//        List<Product> productList=null;
//        try {
//            productList= productService.findAllProduct();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //模拟当前页是第一页
        String currentPageStr=request.getParameter("currentPage");
        System.out.println("currentPageStr: "+currentPageStr);
        if(currentPageStr==null){
            currentPageStr="1";
        }
        int currentPage=Integer.parseInt(currentPageStr);
        //认为每页显示12条
        String currentCountStr=request.getParameter("currentCount");
        int currentCount;

        if(currentCountStr==null){
            currentCount=12;
        }else {
            currentCount=Integer.parseInt(currentCountStr);
        }


        PageBean<Product> pageBean=null;
        try {
            pageBean=productService.findPageBean(currentPage,currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        request.setAttribute("totalPage",totalPage);
//        request.setAttribute("currentPage",currentPage);
//        request.setAttribute("productList",productList);
        //将上述三个属性，封装为一个pageBean
        request.setAttribute("pageBean",pageBean);


        request.getRequestDispatcher("/product_list.jsp").forward(request,response);

    }
}
