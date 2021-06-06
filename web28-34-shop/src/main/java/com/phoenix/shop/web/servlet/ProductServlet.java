package com.phoenix.shop.web.servlet;

import com.phoenix.shop.domain.Product;
import com.phoenix.shop.service.ProductService;
import com.phoenix.shop.utils.CookieUtils;
import com.phoenix.shop.vo.PageBean;
import com.phoenix.shop.web.base.BaseServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProductServlet extends BaseServlet {

    private ProductService productService=new ProductService();

    public String findById(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        String pageNumber=request.getParameter("pageNumber");
        String cid=request.getParameter("cid");
        Product product=null;
        try {
            product = productService.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //把查到的每一个product放到list，作为history
//        HttpSession session = request.getSession();
//        Object sessionProductList = session.getAttribute("historyProductList");
//        System.out.println(sessionProductList);
//        if(sessionProductList!=null){
//            ((List<Product>)sessionProductList).add(product);
//        }else {
//            sessionProductList=new ArrayList<>();
//            ((List<Product>)sessionProductList).add(product);
//        }
//        session.setAttribute("historyProductList",sessionProductList);

        //历史记录cookie的做法
        Cookie historyProductIds = CookieUtils.getCookie(request.getCookies(), "historyProductIds");
        String cookieStr="";
        if(historyProductIds!=null){
            // 1-3-2  1是最新访问的
            // 本次访问的是8   》》 8-1-3-2
            // 本次访问的是3   》》 3-1-2
            String productIdsValue = historyProductIds.getValue();
            // 将productIdsValue拆成一个数组
            String[] split = productIdsValue.split("-");
            LinkedList<String> list=new LinkedList<>(Arrays.asList(split));
            if(list.contains(product.getPid())){
                // 已经包含该商品
                list.remove(product.getPid());
            }else {
                if(list.size()>6){
                    list.removeLast();
                }
            }
            list.addFirst(id);
            // 将【1，2，3 】转成1-2-3
            StringBuffer stringBuffer=new StringBuffer();
            for(int i=0;i<list.size()&&i<7;i++){
                stringBuffer.append(list.get(i));
                stringBuffer.append("-");//1-2-3-
            }
            cookieStr=stringBuffer.substring(0,stringBuffer.length()-1);
        }else {
            cookieStr=id;
        }
        // 创建cookie 回写
        Cookie cookie=new Cookie("historyProductIds",cookieStr);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(60*60*24*7);
        response.addCookie(cookie);
        request.setAttribute("productInfo",product);
        request.setAttribute("pageNumber",pageNumber);
        request.setAttribute("cid",cid);
        return "/jsp/product_info.jsp";
    }

    public String findByCid(HttpServletRequest request,HttpServletResponse response){
        String cid=request.getParameter("cid");
        System.out.println("-----------------------------ci:-----------------"+cid);
        String pageNumberStr=request.getParameter("pageNumber");
        Integer pageNumber=1;
        if(pageNumberStr!=null){
            pageNumber=Integer.parseInt(pageNumberStr);
        }
        Integer pageSize=12;
        List<Product> productList=null;
        PageBean<Product> pageBean=null;
        try {
            pageBean=productService.findByCid(cid,pageNumber,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 获得所有的historyProductIds的cookie
        Cookie historyCookie = CookieUtils.getCookie(request.getCookies(), "historyProductIds");
        if(historyCookie==null){


        }else {
            String historyCookieValue = historyCookie.getValue();
            String[] ids = historyCookieValue.split("-");
            List<Product> historyProductList=null;
            try {
                historyProductList = productService.findByIds(ids);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("historyProductList",historyProductList);
        }


        request.setAttribute("pageBean",pageBean);
        request.setAttribute("cid",cid);
        return "/jsp/product_list.jsp";
    }

}
