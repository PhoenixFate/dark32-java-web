package com.phoenix.order.interceptor;

import com.phoenix.cart.service.CartService;
import com.phoenix.common.utils.CookieUtils;
import com.phoenix.common.utils.JsonUtils;
import com.phoenix.item.pojo.Item;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginHandler implements HandlerInterceptor {

    @Value("${SSO_ADDRESS}")
    private String SSO_ADDRESS;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CartService cartService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //登录 返回true
        //从cookie取user_token
        String userToken = CookieUtils.getCookieValue(httpServletRequest, "user_token");

        //判断token是否存在
        if(StringUtils.isBlank(userToken)){
            //token不存在
            //跳转到sso登录页面，用户登录成功后，跳转到当前的url
            httpServletResponse.sendRedirect(SSO_ADDRESS+"/user/login/page?redirect="+httpServletRequest.getRequestURL());
            return false;
        }
        //存在
        //调sso查询token是否过期
        User user = tokenService.getUserByToken(userToken);
        if(user==null){
            //过期
            //跳转到sso登录页面，用户登录成功后，跳转到当前的url
            httpServletResponse.sendRedirect(SSO_ADDRESS+"/user/login/page?redirect="+httpServletRequest.getRequestURL());
            return false;
        }
        //没过期
        //把user写入request
        httpServletRequest.setAttribute("user",user);
        //判断cookie中是否有购物车数据
        String cart = CookieUtils.getCookieValue(httpServletRequest, "cart", true);
        if(StringUtils.isNotBlank(cart)){
            //如果有，则合并到服务端
            List<Item> itemsFromCookie = JsonUtils.jsonToList(cart, Item.class);
            //合并redis和cookie中的数据
            cartService.mergeCart(user.getId(),itemsFromCookie);
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,"cart");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

    }
}
