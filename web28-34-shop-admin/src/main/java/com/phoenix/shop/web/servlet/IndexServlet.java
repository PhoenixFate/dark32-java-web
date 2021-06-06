package com.phoenix.shop.web.servlet;

import com.phoenix.shop.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends BaseServlet {

    /**
     * 默认方法，用于子类复写
     * @param request
     * @param response
     * @return
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/admin/index.jsp";
    }


}
