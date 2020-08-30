package com.phoenix.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class QuickFilter1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //1.获得web.xml中的filter的名称<filter-name>
        String filterName = filterConfig.getFilterName();
        //2.获得当前filter初始化参数
        String aaa = filterConfig.getInitParameter("aaa");
        //3.获得ServletContext
        ServletContext servletContext = filterConfig.getServletContext();

        System.out.println("quick filter 初始化");
        System.out.println("filter name: "+filterName);
        System.out.println("init parameter: "+aaa);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("quick filter is running!");
        //放行请求
        filterChain.doFilter(servletRequest,servletResponse);


    }

    @Override
    public void destroy() {
        System.out.println("quick filter 被销毁");
    }
}
