package com.phoenix.service;

import com.phoenix.dao.AdminLoginDao;
import com.phoenix.domain.AdminUser;

import java.sql.SQLException;

public class AdminLoginService {

    private AdminLoginDao adminLoginDao=new AdminLoginDao();
    public AdminUser login(String username, String password) throws SQLException {
        AdminUser user = adminLoginDao.login(username, password);
        return user;
    }

}
