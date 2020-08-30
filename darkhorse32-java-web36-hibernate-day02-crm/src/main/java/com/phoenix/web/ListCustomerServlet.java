package com.phoenix.web;

import com.phoenix.domain.Customer;
import com.phoenix.service.CustomerService;
import com.phoenix.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListCustomerServlet extends HttpServlet {

    private CustomerService customerService=new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用dao查询所有
        List<Customer> customerList = customerService.findAllCriteria();
        //将list放入request域
        req.setAttribute("customerList",customerList);
        //转发到list页面显示数据
        req.getRequestDispatcher("/jsp/customer/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
