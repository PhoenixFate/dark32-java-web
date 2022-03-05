package com.phoenix.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class EncodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        //在传递request之前对request的getParameter方法进行增强
        /*
        * 通过装饰者模式进行增强（包装）：
        * 1.增强类与被增强类需要继承同一个类
        * 2.在增强类中传入被增强的类
        * 3.需要增强的方法重写，不需要增强的方法调用被增强的方法;
        *
        * */

        //被增强对象：httpServletRequest
        //增强的对象：
        HttpServletRequest httpServletRequest=(HttpServletRequest)req;
        EnhanceRequest enhanceRequest=new EnhanceRequest(httpServletRequest);
        chain.doFilter(enhanceRequest, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

//专门用来增强的类：HttpServletRequestWrapper
class  EnhanceRequest extends HttpServletRequestWrapper {

    private HttpServletRequest httpServletRequest;
    public EnhanceRequest(HttpServletRequest request) {
        super(request);
        this.httpServletRequest=request;
    }

    //对getParameter增强
    @Override
    public String getParameter(String name) {
        String parameter=httpServletRequest.getParameter(name);//乱码
        //对乱码的parameter进行重新编码
        try {
            parameter=new String(parameter.getBytes("iso8859-1"), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return parameter;
    }
}