package com.phoenix.dao;

import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UserDao {
    public Long checkUsername(String username) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from user where username=?";
        Long count = (Long)queryRunner.query(sql, new ScalarHandler(), username);
        return count;
    }
}
