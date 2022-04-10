package com.phoenix;

import org.junit.Test;
import java.sql.*;

public class QueryAll {


    @Test
    public void testQueryAll(){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try{
            //1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            String url ="jdbc:mysql://www.bytes-space.com:3306/web08?useUnicode=true&characterEncoding=utf8&useSSL=false";
            String username="root";
            String password="centos123qwer";
            connection= DriverManager.getConnection(url,username,password);
            System.out.println(connection);

            //3.获取执行sql语句对象
            statement = connection.createStatement();

            //4.编写sql语句
            String sql="select * from tbl_user";

            //5.执行sql语句
            resultSet = statement.executeQuery(sql);

            //6.编辑结果集
            while(resultSet.next()){
                System.out.println("name: "+resultSet.getString("name")+"; password: "+resultSet.getString("password"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
