package com.phoenix.review;

import javax.servlet.*;
import java.io.IOException;


public class QuickFilter1 implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---------------review quick 1: config----------------");
        System.out.println(filterConfig.getFilterName());
        System.out.println(filterConfig.getInitParameter("aaa"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("review filter1");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
