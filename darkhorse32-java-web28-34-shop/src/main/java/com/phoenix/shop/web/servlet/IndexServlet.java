package com.phoenix.shop.web.servlet;

import com.phoenix.shop.domain.Category;
import com.phoenix.shop.domain.Product;
import com.phoenix.shop.service.CategoryService;
import com.phoenix.shop.service.ProductService;
import com.phoenix.shop.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class IndexServlet extends BaseServlet {

    private ProductService productService=new ProductService();
    private CategoryService categoryService=new CategoryService();
    /**
     * 默认方法，用于子类复写
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Product> hotProductList=null;
        try {
            hotProductList = productService.hotProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Product> newProductList=null;
        try {
            newProductList = productService.newProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Category> categoryList=null;
        try {
            categoryList = categoryService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("hotProductList",hotProductList);
        request.setAttribute("newProductList",newProductList);
        return "/jsp/index.jsp";
    }
}
