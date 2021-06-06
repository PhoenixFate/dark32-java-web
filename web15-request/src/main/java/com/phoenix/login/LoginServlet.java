package com.phoenix.login;

import com.phoenix.register.User;
import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user=null;
        //2.调用业务方法进行用户查询
        user=this.login(username,password);
        System.out.println("user----------------------------------------login-----------------:"+user);
        //3.通过user是否为空，判断用户名密码是否正确
        if(user!=null){
            //用户名密码正确
            //登录成功 跳转到首页
            response.sendRedirect(request.getContextPath()+"/index.jsp");

        }else {
            //用户名或密码错误
            //提示错误 跳回login.jsp
            //使用转发 转发到login.jsp 向request域中存储错误信息
            request.setAttribute("loginInfo","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    public User login(String username,String password){
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user where username=? and password=?";
        User user = null;
        try {
            user = queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
