package com.phoenix.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 此异常处理是springmvc提供的
 * 异常处理器的自定义实现类
 */
public class CustomerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //obj是 包名+类名+方法名（形参） 的字符串

        ModelAndView modelAndView = new ModelAndView();
        //判断异常类型
        if (ex instanceof CustomerException) {
            //属于自定义异常
            CustomerException customerException = (CustomerException) ex;
            modelAndView.addObject("error", customerException.getMessage());
        } else {
            modelAndView.addObject("error", "未知异常");
        }
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
