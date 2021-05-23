package com.phoenix.shop.dao;

import com.phoenix.shop.domain.User;
import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UserDao {

    public User login(String username, String password) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from user where username =? and password=?";
        User user = queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
        return user;
    }

    public Integer register(User user) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="insert into user value(?,?,?,?,?,?,?,?,?,?) ";
        int count = queryRunner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName()
                , user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode());
        return count;
    }

    public Integer active(String code) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="update user set state=1 where code =?";
        int count = queryRunner.update(sql,code);
        return count;
    }

    public Integer checkUsername(String username) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from user where username=?";
        Long count = (Long) queryRunner.query(sql, new ScalarHandler(), username);
        return count.intValue();
    }
}
