package com.phoenix.dao;

import com.phoenix.domain.User;

public interface UserDao {

    User getUserByCode(String userCode);

    User getUserByCode2(String userCode);

}
