package com.phoenix.domain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestCustomerActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将customer放入session中
        HttpSession session = request.getSession();
        Customer customer=new Customer();
        customer.setId("100");
        customer.setName("customer");
        session.setAttribute("customer",customer);
        System.out.println("customer 被放入session中");
    }
}
