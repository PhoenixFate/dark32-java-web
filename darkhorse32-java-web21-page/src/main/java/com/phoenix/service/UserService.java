package com.phoenix.service;

import com.phoenix.dao.UserDao;

import java.sql.SQLException;

public class UserService {
    public boolean checkUsername(String username) throws SQLException {
        UserDao userDao=new UserDao();
        Long count = userDao.checkUsername(username);
        return count > 0;
    }
}
