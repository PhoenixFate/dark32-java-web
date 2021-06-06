package com.phoenix.review;

import com.phoenix.domain.User;
import com.phoenix.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class ReviewLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 验证验证码
        String sessionCheckCode =(String) req.getSession().getAttribute("sessionCheckCode");
        String checkCode = req.getParameter("checkCode");
        if(sessionCheckCode!=null && checkCode!=null){
            if(sessionCheckCode.equals(checkCode)){
                // 验证码正确

            }else {
                req.setAttribute("loginInfo","验证码错误");
            }
        }else {
            req.setAttribute("loginInfo","验证码错误");
        }






        req.setCharacterEncoding("utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        UserService userService=new UserService();
        User user=null;
        try {
            user  = userService.login(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user==null){
            // 用户名或者密码错误
            req.setAttribute("loginInfo","用户名或者密码错误");
            req.getRequestDispatcher("/login").forward(req,resp);
        }else {
            // 判断是否勾选自动登陆
            String autoLogin = req.getParameter("autoLogin");
            if(autoLogin!=null){
                // 勾选了自动登陆
                // 在cookie中设置username和password；
                //cookie无法存储中文!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //需要对用户名和密码进行编码
                //对中文用户名进行编码
                Cookie usernameCookie=new Cookie("usernamer", URLEncoder.encode(username,"utf-8"));
                Cookie passwordCookie=new Cookie("passowrd",URLEncoder.encode(password,"utf-8"));

                // 设置cookie持久化时间 （单位： s）
                usernameCookie.setMaxAge(60*60);
                passwordCookie.setMaxAge(60*60);
                // 设置cookie携带路径
                usernameCookie.setPath(req.getContextPath());
                passwordCookie.setPath(req.getContextPath());

                resp.addCookie(usernameCookie);
                resp.addCookie(passwordCookie);
            }

            //重定向 到首页
            resp.sendRedirect("/index.jsp");
        }

//        super.doPost(req, resp);
    }
}
