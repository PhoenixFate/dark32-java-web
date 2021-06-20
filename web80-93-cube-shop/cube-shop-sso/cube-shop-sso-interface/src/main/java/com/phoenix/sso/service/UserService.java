package com.phoenix.sso.service;

public interface UserService {

    Boolean checkUsername(String username);

    Boolean checkEmail(String email);

    Boolean checkPhone(String phone);

}
