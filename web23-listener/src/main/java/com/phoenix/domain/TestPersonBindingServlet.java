package com.phoenix.domain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TestPersonBindingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将person绑定到session中
        HttpSession session = request.getSession();
        Person person=new Person();
        person.setId("111");
        person.setName("lisi");
        session.setAttribute("person",person);

        session.removeAttribute("person");
    }
}
