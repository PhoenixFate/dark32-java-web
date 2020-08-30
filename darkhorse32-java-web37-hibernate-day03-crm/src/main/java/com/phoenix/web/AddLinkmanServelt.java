package com.phoenix.web;

import com.phoenix.domain.Linkman;
import com.phoenix.service.LinkmanService;
import com.phoenix.service.impl.LinkmanServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AddLinkmanServelt extends HttpServlet {

    private LinkmanService linkmanService=new LinkmanServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得联系人数据
        Linkman linkman=new Linkman();
        String customerId = req.getParameter("customerId");
        try {
            BeanUtils.populate(linkman,req.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //保存
        //调用service保存linkman
        linkmanService.save(linkman,new Long(customerId));
        //保存成功重定向到联系人列表
        resp.sendRedirect(req.getContextPath()+"/listLinkmanServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        this.doGet(req,resp);


    }
}
