package com.phoenix.day30.demo1;

import java.sql.Connection;

import cn.itcast.jdbcutil.JDBCUtilsConfig;

public class TestJDBCUtils {
	public static void main(String[] args) {
		Connection con = JDBCUtilsConfig.getConnection();
		System.out.println(con);
	}
}
