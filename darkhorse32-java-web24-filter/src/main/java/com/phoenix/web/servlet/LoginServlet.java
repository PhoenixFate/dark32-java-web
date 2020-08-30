package com.phoenix.web.servlet;

import com.phoenix.domain.User;
import com.phoenix.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 验证验证码
        String sessionCheckCode =(String) request.getSession().getAttribute("sessionCheckCode");
        String checkCode = request.getParameter("checkCode");
        if(sessionCheckCode!=null && checkCode!=null){
            if(sessionCheckCode.equals(checkCode)){
                // 验证码正确

            }else {
                request.setAttribute("loginInfo","验证码错误");
            }
        }else {
            request.setAttribute("loginInfo","验证码错误");
        }


        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService userService=new UserService();
        User user=null;
        try {
            user= userService.login(username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }








        //登录成功：user不为空
        if(user!=null){
            //跳转到首页
            //将登录的user存到session中
            session.setAttribute("user",user);


            //判断是否勾选自动登录
            String autoLogin = request.getParameter("autoLogin");
            //是
            //创建cookie存储用户名和密码
            if(autoLogin!=null){
                //勾选自动登录
                //cookie无法存储中文!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //需要对用户名和密码进行编码
                //对中文用户名进行编码
                String username_encode = URLEncoder.encode(username, "utf-8");
                System.out.println("username_encode: "+username_encode);
                Cookie cookie_username=new Cookie("cookie_username",username_encode);
                Cookie cookie_password=new Cookie("cookie_password",password);
                //设置cookie的持久化时间
                cookie_username.setMaxAge(60*60);
                cookie_password.setMaxAge(60*60);
                //设置cookie的携带路径
                cookie_username.setPath(request.getContextPath());
                cookie_username.setPath(request.getContextPath());
                response.addCookie(cookie_username);
                response.addCookie(cookie_password);
            }


            //重定向到首页
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            //失败
            //转发到登录页面，继续登录，并且给出错误信息
            request.setAttribute("loginInfo","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }
}
