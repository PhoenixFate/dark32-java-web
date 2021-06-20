package com.phoenix.sso.service;

import com.phoenix.sso.pojo.User;

public interface TokenService {

    User getUserByToken(String token);
}
