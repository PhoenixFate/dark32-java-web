package com.phoenix.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class QuickFilter2 implements Filter {
    public void destroy() {
        System.out.println("quick filter2 被销毁");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("quick filter2 is running");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("quick filter2 初始化");
    }

}
