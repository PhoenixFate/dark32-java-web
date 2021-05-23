package com.phoenix.shop.web.filter;

import com.phoenix.shop.domain.AdminUser;
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

        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        boolean resource=requestURI.endsWith(".jpg")||requestURI.endsWith(".gif")||requestURI.endsWith(".css")||requestURI.endsWith(".js")||requestURI.endsWith(".png");
        boolean login=requestURI.equals(request.getContextPath()+"/")||requestURI.equals(request.getContextPath()+"/admin/index.jsp")||requestURI.contains("adminUserServlet") || requestURI.contains("checkImage");
        if(resource || login){
            filterChain.doFilter(request,servletResponse);
        } else {
            // 校验用户是否登录
            // 校验session是否存在user对象
            HttpSession session = request.getSession();
            AdminUser user = (AdminUser) session.getAttribute("adminUser");
            if(user==null){
                // 跳转登录页面
                response.sendRedirect(request.getContextPath()+"/adminUserServlet?method=loginUI");
                return;
            }else {
                filterChain.doFilter(request,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
