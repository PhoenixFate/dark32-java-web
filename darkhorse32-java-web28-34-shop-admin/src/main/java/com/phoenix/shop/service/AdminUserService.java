package com.phoenix.shop.service;

import com.phoenix.shop.dao.AdminUserDao;
import com.phoenix.shop.domain.AdminUser;

import java.sql.SQLException;

public class AdminUserService {

    private AdminUserDao adminUserDao=new AdminUserDao();

    public AdminUser login(String username,String password) throws SQLException {
        AdminUser adminUser = adminUserDao.login(username, password);
        return adminUser;
    }

}
