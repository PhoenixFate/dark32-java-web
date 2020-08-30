package com.phoenix.web;

import com.phoenix.domain.Category;
import com.phoenix.domain.Product;
import com.phoenix.service.AdminProductService;
import com.phoenix.vo.Condition;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdminSearchProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //1.获得表单的数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2.将散装的数据封装到一个VO实体中
        Condition condition=new Condition();
        try {
            BeanUtils.populate(condition,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(condition);
        //3.将实体传递给Service层
        AdminProductService adminProductService=new AdminProductService();
        List<Product> productList=null;
        try {
            productList = adminProductService.findProductListByCondition(condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("productList",productList);


        //获得所有商品分类的数据
        List<Category> categoryList= null;
        try {
            categoryList = adminProductService.findAllCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList",categoryList);


        //搜索数据回显
        request.setAttribute("condition",condition);

        request.getRequestDispatcher("admin/product/list.jsp").forward(request,response);

    }
}
