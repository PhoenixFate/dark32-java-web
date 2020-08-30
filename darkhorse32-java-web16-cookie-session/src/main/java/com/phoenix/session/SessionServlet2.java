package com.phoenix.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        response.getWriter().println("原先到session id: "+session.getId());
        Object name = session.getAttribute("name");
        System.out.println("name"+name);
        if(name!=null){
            response.getWriter().println("name:  " +name.toString());
        }
        response.getWriter().println("手动销毁session");
        //session.invalidate();// 手动销毁session
        HttpSession session1 = request.getSession();
        response.getWriter().println("新的session id: "+session1.getId());
    }
}
