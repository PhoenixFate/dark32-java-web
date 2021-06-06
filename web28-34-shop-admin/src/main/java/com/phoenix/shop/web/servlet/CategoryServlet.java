package com.phoenix.shop.web.servlet;

import com.google.gson.Gson;
import com.phoenix.shop.domain.Category;
import com.phoenix.shop.service.CategoryService;
import com.phoenix.shop.utils.CommonsUtils;
import com.phoenix.shop.utils.JedisPoolUtils;
import com.phoenix.shop.vo.PageBean;
import com.phoenix.shop.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService=new CategoryService();

    public void getAllJson(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        List<Category> categoryList = categoryService.findAll();
        Gson gson=new Gson();
        String json = gson.toJson(categoryList);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(json);
    }


    public String listByPage(HttpServletRequest req, HttpServletResponse resp)  {
        String pageNumberStr=req.getParameter("pageNumber");
        Integer pageNumber=1;
        if(pageNumberStr!=null){
            pageNumber=Integer.parseInt(pageNumberStr);
        }
        Integer pageSize=12;
        PageBean<Category> pageBean=null;
        try {
            pageBean=categoryService.findByPage(pageNumber,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("pageBean",pageBean);
        return "/admin/category/list.jsp";
    }

    public void addCategory(HttpServletRequest req, HttpServletResponse resp)  {
        Category category=new Category();
        try {
            BeanUtils.populate(category,req.getParameterMap());
            category.setCid(CommonsUtils.getUUID());
            Integer count = categoryService.addCategory(category);
            if(count>=1){
                //新增成功
                this.removeRedisCategory();
                resp.sendRedirect(req.getContextPath()+"/categoryServlet?method=listByPage");
                return;
            }
            //新增失败
            resp.sendRedirect(req.getContextPath()+"/admin/error.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void updateCategoryUI(HttpServletRequest req, HttpServletResponse resp)  {
        String id = req.getParameter("id");
        try {
            Category category = categoryService.findById(id);
            req.setAttribute("category",category);
            req.getRequestDispatcher("/admin/category/edit.jsp").forward(req,resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(HttpServletRequest req, HttpServletResponse resp)  {
        Category category=new Category();
        try {
            BeanUtils.populate(category,req.getParameterMap());
            System.out.println("----update category--"+category);
            Integer count = categoryService.updateCategory(category);
            if(count>=1){
                //更新成功
                this.removeRedisCategory();
                resp.sendRedirect(req.getContextPath()+"/categoryServlet?method=listByPage");
            }else {
                //更新失败
                resp.sendRedirect(req.getContextPath()+"/admin/error.jsp");
            }
        } catch (SQLException  | IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void deleteCategory(HttpServletRequest req, HttpServletResponse resp)  {
        String cid=req.getParameter("cid");
        System.out.println("cid: "+cid);
        try {
            Integer count = categoryService.deleteById(cid);
            if(count>=1){
                //更新成功
                this.removeRedisCategory();
                resp.sendRedirect(req.getContextPath()+"/categoryServlet?method=listByPage");
            }else {
                //更新失败
                resp.sendRedirect(req.getContextPath()+"/admin/error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeRedisCategory(){
        // 先从redis中读取categoryList, 如果没有，再从数据库中读取category List，并保存到redis中
        Jedis jedis = JedisPoolUtils.getJedis();
        String categoryListRedis = jedis.get("categoryList");
        if(categoryListRedis!=null){
            jedis.del("categoryList");
        }
    }


}
