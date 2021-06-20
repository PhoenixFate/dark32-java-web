package com.phoenix.sso.service;

import com.phoenix.sso.pojo.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface RegisterService {

    Boolean register(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
