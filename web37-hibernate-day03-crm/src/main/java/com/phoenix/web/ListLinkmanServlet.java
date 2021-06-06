package com.phoenix.web;

import com.phoenix.domain.Linkman;
import com.phoenix.service.LinkmanService;
import com.phoenix.service.impl.LinkmanServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListLinkmanServlet extends HttpServlet {

    private LinkmanService linkmanService=new LinkmanServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        List<Linkman> linkmanList = linkmanService.findAll();
        req.setAttribute("linkmanList",linkmanList);
        req.getRequestDispatcher("/jsp/linkman/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        this.doGet(req,resp);
    }
}
