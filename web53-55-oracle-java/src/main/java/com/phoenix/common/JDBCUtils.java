package com.phoenix.common;

import java.sql.*;

public class JDBCUtils {

    private static String driver="oracle.jdbc.OracleDriver";

    private static String url="jdbc:oracle:thin:@www.bytes-space.com:57521/helowinXDB";

    private static String user="scott";

    private static String password="tiger";

    static {
        //注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void release(Connection collection, Statement statement, ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                resultSet=null;
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                statement=null;
            }
        }
        if(collection!=null){
            try {
                collection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                collection=null;
            }
        }

    }



}
