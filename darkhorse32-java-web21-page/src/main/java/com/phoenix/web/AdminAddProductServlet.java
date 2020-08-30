package com.phoenix.web;

import com.phoenix.domain.Product;
import com.phoenix.service.AdminProductService;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class AdminAddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2.封装数据
        Product product=new Product();
        try {
            BeanUtils.populate(product,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //此位置Product已经封装完毕
        //手动设置表单中没有的数据
        product.setPid(UUID.randomUUID().toString());
        product.setPimage("products/1/c_0039.jpg");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        product.setPdate(simpleDateFormat.format(date));
        product.setPflag(0);//商品是否下架，0表示未下架。

        System.out.println(product);
        //3.传递数据给service层
        AdminProductService adminProductService=new AdminProductService();
        try {
            adminProductService.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //跳转到列表页面
        //request.getRequestDispatcher("").forward(request,response);
        //如果request域中没有存储数据，则建议使用response.sendRedirect();
        response.sendRedirect(request.getContextPath()+"/adminProductList");
    }
}
