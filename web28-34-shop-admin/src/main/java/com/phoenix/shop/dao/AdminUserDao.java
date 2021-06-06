package com.phoenix.shop.dao;

import com.phoenix.shop.domain.AdminUser;
import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminUserDao {

    public AdminUser login(String username,String password) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from admin_user where username=? and password=?";
        AdminUser adminUser = queryRunner.query(sql, new BeanHandler<>(AdminUser.class), username, password);
        return adminUser;
    }

}
