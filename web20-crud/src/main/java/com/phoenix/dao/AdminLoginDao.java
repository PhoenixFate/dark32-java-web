package com.phoenix.dao;

import com.phoenix.domain.AdminUser;
import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminLoginDao {

    private QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());

    public AdminUser login(String username, String password) throws SQLException {
        String sql="select * from admin_user where username=? and password = ?";
        return queryRunner.query(sql, new BeanHandler<>(AdminUser.class), username, password);
    }

}
