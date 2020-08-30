package com.phoenix.service;

import com.phoenix.dao.UserDao;
import com.phoenix.entity.User;

import java.sql.SQLException;

public class UserService {

    public User login(String username, String password) throws SQLException {
        UserDao userDao=new UserDao();
        return userDao.login(username,password);
    }

}
