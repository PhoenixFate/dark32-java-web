package com.phoenix;

import org.junit.Test;

import java.sql.*;

public class TestLogin {

    @Test
    public void myTest(){
        try {
            testLogin("tom","1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void zhuru(){
        try {
            testLogin("tom' or 1='1","1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void zhuru2(){
        try {
            testLogin2("tom' or 1='1","1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void testLogin(String name,String pasword) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url ="jdbc:mysql://47.99.113.229:3306/web08?useUnicode=true&characterEncoding=utf8&useSSL=false";
        Connection connection= DriverManager.getConnection(url,"root","123456");
        Statement statement = connection.createStatement();
        String sql="select * from tbl_user where name='"+name+"' and password='"+pasword+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(sql);
        if(resultSet.next()){
            System.out.println("恭喜登录成功");
        }else {
            System.out.println("登录失败");
        }
        if(resultSet!=null){
            resultSet.close();
        }
        if(statement!=null){
            statement.close();
        }
        if(connection!=null){
            connection.close();
        }
    }

    public void testLogin2(String name,String pasword) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url ="jdbc:mysql://47.99.113.229:3306/web08?useUnicode=true&characterEncoding=utf8&useSSL=false";
        Connection connection= DriverManager.getConnection(url,"root","123456");
        Statement statement = connection.createStatement();
        String sql="select * from tbl_user where name=?&password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,pasword);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("恭喜登录成功");
        }else {
            System.out.println("登录失败");
        }
        if(resultSet!=null){
            resultSet.close();
        }
        if(statement!=null){
            statement.close();
        }
        if(connection!=null){
            connection.close();
        }
    }

}
