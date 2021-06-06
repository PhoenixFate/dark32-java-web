package com.phoenix.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应编码：
        response.setContentType("text/html;charset=utf-8");


        //获得ServletContext对象
        ServletContext servletContext = this.getServletContext();

        //1.获得web应用全局初始化参数
        String initParameter = servletContext.getInitParameter("driver");
        response.getWriter().write("<b>driver:</b>"+initParameter+"<br/>");
        //2.获得web应用中任何资源的绝对路径（!important)
        String realPathA = servletContext.getRealPath("a.txt");
        response.getWriter().write("a:realPath:"+realPathA+"<br/>");

        String realPathB = servletContext.getRealPath("WEB-INF/b.txt");
        response.getWriter().write("b:realPath:"+realPathB+"<br/>");

        String realPathC = servletContext.getRealPath("WEB-INF/classes/c.txt");
        response.getWriter().write("c:realPath:"+realPathC+"<br/>");

        //获取不到d.txt
//        String realPathD = servletContext.getRealPath("d.txt");
//        response.getWriter().write("d:realPath:"+realPathD+"<br/>");

        //在读取src(classes)下的资源时可以通过类加载器
        //getResource()参数是一个相对地址，但相对于classes
        //  在web工程中 / \都是可以都的
        String path = MyContextServlet.class.getClassLoader().getResource("c.txt").getPath();
        response.getWriter().write("通过类加载器加载资源并获取路径："+path);

        //3.域对象，servletContext
        servletContext.setAttribute("name","zhangsan");

    }
}
