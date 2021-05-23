package com.phoenix.shop.web.servlet;

import com.google.gson.Gson;
import com.phoenix.shop.domain.Order;
import com.phoenix.shop.domain.OrderItem;
import com.phoenix.shop.service.OrderService;
import com.phoenix.shop.vo.PageBean;
import com.phoenix.shop.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderServlet extends BaseServlet {

    private OrderService orderService=new OrderService();

    public String listByPage(HttpServletRequest req, HttpServletResponse resp)  {
        String pageNumberStr=req.getParameter("pageNumber");
        Integer pageNumber=1;
        if(pageNumberStr!=null){
            pageNumber=Integer.parseInt(pageNumberStr);
        }
        Integer pageSize=6;
        PageBean<Order> pageBean=null;
        try {
            pageBean=orderService.findByPage(pageNumber,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("pageBean",pageBean);
        return "/admin/order/list.jsp";
    }

    public void findDetailByOid(HttpServletRequest req, HttpServletResponse resp)  {
        String oid=req.getParameter("oid");
        try {
            List<Map<String, Object>>  orderItemList = orderService.findDetailByOid(oid);
            Gson gson=new Gson();
            String json = gson.toJson(orderItemList);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(json);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }




}
