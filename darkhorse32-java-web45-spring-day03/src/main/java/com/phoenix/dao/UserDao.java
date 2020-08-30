package com.phoenix.dao;

import com.phoenix.bean.User;

import java.util.List;

public interface UserDao {

    Integer save(User user);

    Integer update(User user);

    Integer delete(Integer id);

    User getById(Integer id);

    Integer getTotalCount();

    List<User> getAll();

}
