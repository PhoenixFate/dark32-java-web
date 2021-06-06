package com.phoenix.shop.web.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获得请求参数method
        String methodName=req.getParameter("method");
        // *默认方法名
        if(methodName==null){
            methodName="execute";
        }
        //2. 获得当前运行类，需要指定的方法
        try {
            Method method=this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            // 3.执行方法
            String jspPath=(String) method.invoke(this,req,resp);
            //4. 如果子类有返回值，将请求到指定的jsp页面
            if(jspPath!=null){
                req.getRequestDispatcher(jspPath).forward(req,resp);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 默认方法，用于子类复写
     * @param request
     * @param response
     * @return
     */
    public String execute(HttpServletRequest request,HttpServletResponse response){
        return null;
    }



}
