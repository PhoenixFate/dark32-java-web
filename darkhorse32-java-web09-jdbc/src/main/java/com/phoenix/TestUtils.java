package com.phoenix;

import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUtils {

    @Test
    public void testV1(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection= JDBCUtils_V1.getConnection();
            String sql="select * from tbl_user where uid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("name: "+resultSet.getString("name")+"; password: "+resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_V1.release(resultSet,preparedStatement,connection);
        }
    }


    @Test
    public void testV2(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection= JDBCUtils_V2.getConnection();
            String sql="select * from tbl_user where uid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("name: "+resultSet.getString("name")+"; password: "+resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_V1.release(resultSet,preparedStatement,connection);
        }
    }

    @Test
    public void testV3(){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection= JDBCUtils_V3.getConnection();
            String sql="select * from tbl_user where uid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("name: "+resultSet.getString("name")+"; password: "+resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_V1.release(resultSet,preparedStatement,connection);
        }
    }


}
