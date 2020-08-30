package com.phoenix.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyDataSourceUtils {
	//从连接池中获得connection
	private static DataSource dataSource=new ComboPooledDataSource();

	private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();

	public static Connection getConnection() throws SQLException {

		return dataSource.getConnection();
	}

	//开启事务
	public static void startTransaction() throws SQLException {
		Connection connection = getCurrentConnection();
		connection.setAutoCommit(false);
	}

	//回滚事务
	public static void rollback() throws SQLException {
		Connection connection = getCurrentConnection();
		if(connection!=null){
			connection.rollback();
		}
	}

	//提交事务
	public static void commit() throws SQLException {
		Connection connection = getCurrentConnection();
		if(connection!=null){
			connection.commit();
			//将connection从ThreadLocal中移除
			threadLocal.remove();
			connection.close();
		}

	}


	//获取当前线程上绑定的connection
	public static Connection getCurrentConnection() throws SQLException {
		//先从ThreadLocal寻找当前线程是否有对应的connection
		Connection connection = threadLocal.get();
		if(null==connection){
			//获得一个新的connection对象
			connection=getConnection();
			//将connection对象绑定到threadLocal(map)上
			threadLocal.set(connection);
		}
		return connection;
	}

}
