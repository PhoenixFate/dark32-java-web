package com.phoenix.order.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 */
public class GlobalException implements HandlerExceptionResolver {

    private static final Logger logger= LoggerFactory.getLogger(GlobalException.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception exception) {
        //打印错误到控制台
        exception.printStackTrace();

        //把错误写入日志
        logger.debug("测试输出的日志-----------");
        logger.info("系统发生异常了------------");
        logger.error("系统发生异常",exception);

        //发邮件
        //使用jmail发送邮件

        //发短信

        //展示友好的错误页面
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
