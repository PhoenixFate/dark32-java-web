package com.phoenix;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils_V3 {

    public static String driver;
    public static String url;
    public static String username;
    public static String password;

    static {
//        InputStream resourceAsStream = JDBCUtils_V3.class.getResourceAsStream("db.properties");
        InputStream resourceAsStream = JDBCUtils_V3.class.getClassLoader().getResourceAsStream("db.properties");
        System.out.println(resourceAsStream);
        Properties properties=new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
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
