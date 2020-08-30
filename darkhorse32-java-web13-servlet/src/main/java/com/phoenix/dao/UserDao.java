package com.phoenix.dao;

import com.phoenix.entity.User;
import com.phoenix.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import java.sql.SQLException;

public class UserDao {

    public User login(String username, String password) throws SQLException {

        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user where username=? and password =?";
        Object[] params={username,password};
        return queryRunner.query(sql, new BeanHandler<>(User.class), params);
    }
}
