package com.phoenix.jdbc.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Utils {

    public static ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();

    public static ComboPooledDataSource getDataSource(){
        return comboPooledDataSource;
    }

    public static Connection getConnection(){
        try {
            return comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
