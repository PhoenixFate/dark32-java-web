package com.phoenix.dbutils;

import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtilsDemo {
    public static void main(String[] args) {
        //构造函数传入dataSource无法解决事务问题
        QueryRunner queryRunner=new QueryRunner();
        String sql="update account set money=10000 where name='tom' ";
        Connection connection=null;
        try {
            //开启事务
            connection= DataSourceUtils.getConnection();
            connection.setAutoCommit(false);
            int count = queryRunner.update(connection, sql);
            //提交或回滚事务
            if(count>0){
                connection.commit();
            }else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }
}
