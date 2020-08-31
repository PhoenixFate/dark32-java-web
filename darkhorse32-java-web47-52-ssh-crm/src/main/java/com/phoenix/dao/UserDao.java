package com.phoenix.dao;

import com.phoenix.domain.User;

public interface UserDao {

    User getUserByCode(String userCode);

    User getUserByCodePassword(User user);

    User getUserByCode2(String userCode);

    Integer save(User user);
}
