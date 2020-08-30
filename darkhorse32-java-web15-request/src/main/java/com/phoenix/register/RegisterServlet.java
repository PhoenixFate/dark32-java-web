package com.phoenix.register;

import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端get方式乱码解决
        String username=request.getParameter("username");
        //先用iso8859-1编码，再用utf-8解码
        username=new String(username.getBytes("ISO8859-1"),"UTF-8");
        System.out.println(username);

        //1.获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();

        //2.将散装数据封装到JavaBean中
        User user = new User();
        //2.1使用BeanUtils自动映射封装
        //BeanUtils工作原理：将map中的数据 根据key与实体的属性的对应关系封装
        //只要key的名字与实体的属性 的名字一样 就自动封装到实体中
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        user.setUid(UUID.randomUUID().toString());
        //user.setUsername(username);
        System.out.println("user: "+user);

        //现在这个位置 user对象已经封装好了
        //手动封装uid----uuid---随机不重复的字符串32位--java代码生成后是36位

        //3.将参数传递给一个业务方法
        register(user);


        //4.任务你注册成功了
        //跳转到登录界面
        //response.sendRedirect("/web15/login");
        //动态获得应用名
        response.sendRedirect(request.getContextPath()+"/login.jsp");

    }


    //专门是注册的方法
    //主要是操作数据库
    public void register(User user){
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
        try {
            queryRunner.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail()
                    ,null,user.getBirthday(),user.getSex(),1,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
