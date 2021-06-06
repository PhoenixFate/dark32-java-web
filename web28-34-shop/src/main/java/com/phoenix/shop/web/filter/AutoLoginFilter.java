package com.phoenix.shop.web.filter;

import com.phoenix.shop.domain.User;
import com.phoenix.shop.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

public class AutoLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        User user=(User)request.getSession().getAttribute("user");
        if(user!=null){
            // 已经登陆了，不需要自动登陆
            filterChain.doFilter(request,response);
            return;
        }

        Cookie[] cookies = request.getCookies();
        String username=null;
        String password=null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("cookieUsername")){
                    username= URLDecoder.decode(cookie.getValue(),"utf-8");
                }
                if(cookie.getName().equals("cookiePassword")){
                    password=URLDecoder.decode(cookie.getValue(),"utf-8");
                }
            }
        }
        if(username!=null && password!=null){
            UserService userService=new UserService();
            User user2=null;
            try {
                user2 = userService.login(username, password);
                if(user2!=null){
                    // 这时候自动登录
                    request.getSession().setAttribute("user",user2);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
