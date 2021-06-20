package com.phoenix.service;

import com.phoenix.domain.User;

public interface UserService {

    User getUserByCodePassword(User user);

    void saveUser(User user);

}
