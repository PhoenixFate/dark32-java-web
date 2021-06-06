package com.phoenix.review;

import com.phoenix.domain.User;
import com.phoenix.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

public class AutoLoginServlet implements Filter {

    private String username;
    private String password;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            String name = cookie.getName();
            if(name.equals("username")){
                this.username= URLDecoder.decode(cookie.getValue(),"utf-8");
            }
            if(name.equals("password")){
                this.password=URLDecoder.decode(cookie.getValue(),"utf-8");
            }

        }
        if(username!=null && password!=null){
            UserService userService=new UserService();
            User user=null;
            try {
                user = userService.login(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(user!=null){
                // 自动登陆
                request.getSession().setAttribute("user",user);
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
