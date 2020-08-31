package com.phoenix.service;

import com.phoenix.domain.User;

public interface UserService {

    User getUserByCodePassword(User user);

    Integer save(User user);

    Integer mySave(User user);

    User login(User user);
}
