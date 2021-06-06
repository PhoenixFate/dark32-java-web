package com.phoenix.day29Utils.demo1;

import com.phoenix.day29Utils.jdbcutil.JDBCUtilsConfig;

import java.sql.Connection;

public class TestJDBCUtils {
	public static void main(String[] args) {
		Connection con = JDBCUtilsConfig.getConnection();
		System.out.println(con);
	}
}
