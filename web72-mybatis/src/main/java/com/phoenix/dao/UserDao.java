package com.phoenix.dao;

import com.phoenix.pojo.User;

public interface UserDao {
    //通过用户ID查询一个用户
    User selectUserById(Integer id);
}
