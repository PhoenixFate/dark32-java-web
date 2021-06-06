package com.phoenix.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args)  {
        //通过jdbc去控制事务
        Connection connection=null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得connection
            connection = DriverManager.getConnection("jdbc:mysql://47.99.113.229:3306/web19", "root", "123456");

            //手动开启事务
            connection.setAutoCommit(false);


            //3.获得执行平台
            Statement statement = connection.createStatement();
            //4.执行sql
            int count = statement.executeUpdate("update account set money=5000 where name='tom'");
            if(count>=1){
                connection.commit();
            }else {
                connection.rollback();
            }
            //statement.executeUpdate("insert into account valuse(null,'lisi',2008)");


            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }
}
