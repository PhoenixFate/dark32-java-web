package com.phoenix.mapper;

import com.phoenix.pojo.User;

public interface UserMapper {

    User selectUserById(Integer id);
}
