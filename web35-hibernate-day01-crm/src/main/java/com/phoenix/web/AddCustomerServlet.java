package com.phoenix.web;


import com.phoenix.domain.Customer;
import com.phoenix.service.CustomerService;
import com.phoenix.service.impl.CustomerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AddCustomerServlet extends HttpServlet {

    private CustomerService customerService=new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

        //1.获得参数并封装到customer
        Customer customer=new Customer();
        try {
            BeanUtils.populate(customer,req.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //2.调用service保存客户
        customerService.save(customer);

        //3.重定向到客户列表
        resp.sendRedirect(req.getContextPath()+"/ListServlet");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //super.doPost(req, resp);
        this.doGet(req,resp);


    }
}
