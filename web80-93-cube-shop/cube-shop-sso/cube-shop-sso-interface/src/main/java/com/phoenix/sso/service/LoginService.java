package com.phoenix.sso.service;

import java.io.IOException;

public interface LoginService {

    String login(String username, String password) throws IOException;

}
