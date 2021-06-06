package com.phoenix.web.filter;

import com.phoenix.domain.User;
import com.phoenix.service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

public class  AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获得cookie中的用户名的密码 进行登录操作
        HttpServletRequest httpServletRequest=(HttpServletRequest)req;
        Cookie[] cookies = httpServletRequest.getCookies();
        String cookie_username=null;
        String cookie_password=null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                //获得用户名和密码
                if("cookie_username".equals(cookie.getName())){
                    //对username进行解码：
                    //恢复为中文的用户名
                    cookie_username= URLDecoder.decode(cookie.getValue(),"utf-8");
                }
                if("cookie_password".equals(cookie.getName())){
                    cookie_password=cookie.getValue();
                }
            }
        }

        HttpSession session = httpServletRequest.getSession();

        //判断username和password是否为空
        if(cookie_username!=null && cookie_password!=null){
            //登录代码
            UserService userService=new UserService();
            User user=null;
            try {
                user= userService.login(cookie_username,cookie_password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //将登录的user存到session中
            session.setAttribute("user",user);

        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
