package com.phoenix.dao;

import com.phoenix.domain.User;
import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {

    public User login(String username, String password) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user where username=? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), username, password);

        return user;
    }
}
