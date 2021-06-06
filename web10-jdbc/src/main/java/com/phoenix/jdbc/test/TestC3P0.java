package com.phoenix.jdbc.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestC3P0 {

    @Test
    public void test(){
        try {
            ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
            System.out.println(comboPooledDataSource);
            Connection connection = comboPooledDataSource.getConnection();
            System.out.println(connection);
            String sql="select * from tbl_user ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("name: "+resultSet.getString("name")+"; password: "+resultSet.getString("password"));
            }
            connection.close();
            System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
