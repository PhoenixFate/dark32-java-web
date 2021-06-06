package com.phoenix.jdbc.test;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class TestDBCP {

    @Test
    public void test1(){
        try {
            InputStream resourceAsStream = TestDBCP.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties=new Properties();
            properties.load(resourceAsStream);
            DataSource dataSource= BasicDataSourceFactory.createDataSource(properties);
            Connection connection = dataSource.getConnection();
            String sql="select * from tbl_user ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("name: "+resultSet.getString("name")+"; password: "+resultSet.getString("password"));
            }
            connection.close();
            System.out.println(connection);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
