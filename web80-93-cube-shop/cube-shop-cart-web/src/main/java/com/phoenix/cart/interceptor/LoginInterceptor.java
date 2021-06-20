package com.phoenix.cart.interceptor;

import com.phoenix.common.utils.CookieUtils;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //执行handler之前调用此方法
        //返回true，放行；返回false，拦截
        //判断是否登录
        //1.从cookie取token
        String userToken= CookieUtils.getCookieValue(httpServletRequest,"user_token");
        //2.如果没有取到token，直接放行
        if(StringUtils.isBlank(userToken)){
            return true;
        }
        //3.如果取到，需要调用sso服务，根据token取用户信息
        User user = tokenService.getUserByToken(userToken);

        //4.没有取到用户信息；登录过期，直接放行
        if(user==null){
            return true;
        }
        //5.取到用户信息，是登录状态
        httpServletRequest.setAttribute("user",user);

        //6.把用户信息放到request中，只需要在controller中判断是否包含user信息；然后放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

        //handler执行之后，返回modelAndView 之前
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

        //完成处理， 返回modelAndView之后

        //可以在此处理异常
    }
}
