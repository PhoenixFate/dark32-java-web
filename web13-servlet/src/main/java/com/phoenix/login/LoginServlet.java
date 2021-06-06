package com.phoenix.login;

import com.phoenix.entity.User;
import com.phoenix.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        int count=0;
        servletContext.setAttribute("count",count);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应编码：
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //1.获得用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println(username);
        System.out.println(password);
        for(String hobby:hobbies){
            System.out.println(hobby);
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry:parameterMap.entrySet()){
            System.out.println("key: "+entry.getKey());
            for(String value:entry.getValue()){
                System.out.println("value: "+value);
            }
        }

        //2.验证用户名和密码
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user where username=? and password =?";
        Object[] params={username,password};//参数
        User user=null;
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(null!=user) {
            //用户登录成功
            //3.返回用户的所有信息
            ServletContext servletContext = this.getServletContext();
            Integer count=(Integer)servletContext.getAttribute("count");
            count++;
            response.getWriter().write(user.toString()+"---you are success login person ：中文测试; count:" +count);
            servletContext.setAttribute("count",count);
        }else {
            //用户登入失败
            response.getWriter().write("用户或密码错误");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
