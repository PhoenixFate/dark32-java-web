package com.phoenix.shop.web.filter;

import com.phoenix.shop.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PrivilegeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        // 校验用户是否登录
        // 校验session是否存在user对象
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(user==null){
            // 跳转登录页面
            response.sendRedirect(request.getContextPath()+"/userServlet?method=loginUI");
            return;
        }
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
