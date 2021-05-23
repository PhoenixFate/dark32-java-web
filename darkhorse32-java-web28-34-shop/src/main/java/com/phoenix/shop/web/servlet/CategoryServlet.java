package com.phoenix.shop.web.servlet;

import com.google.gson.Gson;
import com.phoenix.shop.domain.Category;
import com.phoenix.shop.service.CategoryService;
import com.phoenix.shop.utils.JedisPoolUtils;
import com.phoenix.shop.web.base.BaseServlet;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService=new CategoryService();

    public void findAll(HttpServletRequest request, HttpServletResponse response){
        List<Category> categoryList=null;
        // 先从redis中读取categoryList, 如果没有，再从数据库中读取category List，并保存到redis中
        Jedis jedis = JedisPoolUtils.getJedis();
        String categoryListRedis = jedis.get("categoryList");
        if(categoryListRedis==null){
            try {
                System.out.println("category list 调用数据库");
                categoryList = categoryService.getAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            categoryListRedis = new Gson().toJson(categoryList);
            jedis.set("categoryList",categoryListRedis);
        }
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(categoryListRedis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
