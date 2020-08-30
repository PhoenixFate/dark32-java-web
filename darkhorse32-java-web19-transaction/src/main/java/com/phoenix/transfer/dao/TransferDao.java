package com.phoenix.transfer.dao;

import com.phoenix.utils.MyDataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.SQLException;

public class TransferDao {

    public void out(String out, double money) throws SQLException {
        QueryRunner queryRunner=new QueryRunner();
        //Connection connection= DataSourceUtils.getConnection();
        String sql="update account set money= money-? where name=?";
        queryRunner.update(MyDataSourceUtils.getCurrentConnection(),sql,money,out);
    }

    public void in(String in, double money) throws SQLException {
        QueryRunner queryRunner=new QueryRunner();
        //Connection connection= DataSourceUtils.getConnection();
        String sql="update account set money= money+? where name=?";
        queryRunner.update(MyDataSourceUtils.getCurrentConnection(),sql,money,in);
    }
}
