package com.phoenix.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //方法前
        System.out.println("方法前1");
        //判断用户是否登陆  如果没有登陆  重定向到登陆页面   不放行   如果登陆了  就放行了
        //URL  http://localhost:8080/springmvc/login.action
        //URI /login.action
        String requestURI = request.getRequestURI();
        if(!requestURI.contains("/login")){
            String username = (String) request.getSession().getAttribute("USER_SESSION");
            if(null == username){
                response.sendRedirect(request.getContextPath() + "/toLogin.action");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //方法后
        System.out.println("方法后1");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //渲染页面后
        System.out.println("页面渲染后1");
    }
}
