package com.phoenix.day30.demo1;

import com.phoenix.day30.jdbcutil.JDBCUtilsConfig;

import java.sql.Connection;

public class TestJDBCUtils {
	public static void main(String[] args) {
		Connection con = JDBCUtilsConfig.getConnection();
		System.out.println(con);
	}
}
