package com.phoenix.shop.web.servlet;

import com.google.gson.Gson;
import com.phoenix.shop.domain.Category;
import com.phoenix.shop.domain.Product;
import com.phoenix.shop.service.CategoryService;
import com.phoenix.shop.service.ProductService;
import com.phoenix.shop.utils.CommonsUtils;
import com.phoenix.shop.utils.JedisPoolUtils;
import com.phoenix.shop.vo.PageBean;
import com.phoenix.shop.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public class ProductServlet extends BaseServlet {

    private ProductService productService=new ProductService();

    public String listByPage(HttpServletRequest req, HttpServletResponse resp)  {
        String pageNumberStr=req.getParameter("pageNumber");
        Integer pageNumber=1;
        if(pageNumberStr!=null){
            pageNumber=Integer.parseInt(pageNumberStr);
        }
        Integer pageSize=12;
        PageBean<Product> pageBean=null;
        try {
            Jedis jedis=JedisPoolUtils.getJedis();
            Gson gson=new Gson();
            String productListByPage = jedis.get(req.getContextPath()+":productListByPage" + "-" + pageNumber + "-" + pageSize);
            if(productListByPage==null){
                pageBean=productService.findByPage(pageNumber,pageSize);
                jedis.set(req.getContextPath()+":productListByPage" + "-" + pageNumber + "-" + pageSize,gson.toJson(pageBean));
            }else {
                pageBean=gson.fromJson(productListByPage,PageBean.class);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("pageBean",pageBean);
        return "/admin/product/list.jsp";
    }

    public String addProductUI(HttpServletRequest req, HttpServletResponse resp)  {
        CategoryService categoryService=new CategoryService();
        try {
            List<Category> categoryList = categoryService.findAll();
            System.out.println(categoryList);
            req.setAttribute("categoryList",categoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/admin/product/add.jsp";
    }


    public void addProduct(HttpServletRequest req, HttpServletResponse resp)  {
        Map<String, String[]> parameterMap = req.getParameterMap();
        for(Map.Entry<String,String[]> entry:parameterMap.entrySet()){
            System.out.println("key: "+entry.getKey());
            for(String value:entry.getValue()){
                System.out.println(value);
            }
        }
        Product product=new Product();
        try {
            BeanUtils.populate(product,req.getParameterMap());
            product.setPid(CommonsUtils.getUUID());
            product.setPdate(new Date());
            Integer count = productService.addProduct(product);
            if(count>=1){
                //新增成功
                this.deleteRedisProduct(req.getContextPath());
                resp.sendRedirect(req.getContextPath()+"/productServlet?method=listByPage");
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


    public void deleteProduct(HttpServletRequest req, HttpServletResponse resp)  {
        String pid=req.getParameter("pid");
        try {
            Integer count = productService.deleteById(pid);
            if(count>=1){
                //更新成功
                this.deleteRedisProduct(req.getContextPath());
                resp.sendRedirect(req.getContextPath()+"/productServlet?method=listByPage");
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

    private void deleteRedisProduct(String prefix)  {
        Jedis jedis = JedisPoolUtils.getJedis();
        Set<String> set = jedis.keys( prefix+":productListByPage*");
        System.out.println("----------------------------"+set);
        for (String keyStr : set) {
            System.out.println(keyStr);
            jedis.del(keyStr);
        }

    }

}
