package com.phoenix;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtils_V2 {

    public static String driver;
    public static String url;
    public static String username;
    public static String password;

    static {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("db");
        driver=resourceBundle.getString("driver");
        url= resourceBundle.getString("url");
        username=resourceBundle.getString("username");
        password = resourceBundle.getString("password");
        System.out.println(driver);
        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
    }


    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
