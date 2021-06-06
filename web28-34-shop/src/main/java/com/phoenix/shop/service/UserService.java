package com.phoenix.shop.service;

import com.phoenix.shop.dao.UserDao;
import com.phoenix.shop.domain.User;

import java.sql.SQLException;

public class UserService {

    private UserDao userDao=new UserDao();

    public User login(String username,String password) throws SQLException {
        User user = userDao.login(username, password);
        return user;
    }

    public Integer register(User user) throws SQLException {
        Integer count = userDao.register(user);
        return count;
    }

    public Integer active(String code) throws SQLException {
        Integer count=userDao.active(code);
        return count;
    }

    public Integer checkUsername(String username) throws SQLException {
        Integer count = userDao.checkUsername(username);
        return count;
    }
}
