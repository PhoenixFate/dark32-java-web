package com.phoenix.sso.service.impl;

import com.phoenix.common.jedis.JedisClient;
import com.phoenix.common.utils.JsonUtils;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${APPLICATION_NAME}")
    private String APPLICATION_NAME;

    @Value("${TOKEN}")
    private String TOKEN;

    @Value("${EXPIRE_TIME}")
    private Integer EXPIRE_TIME;

    @Override
    public User getUserByToken(String token) {
        //根据token从redis中取user的信息
        String userInfo = jedisClient.get(APPLICATION_NAME+":"+TOKEN+":"+token);
        //取不到用户信息，登录已经过期，返回登录过期
        if(StringUtils.isBlank(userInfo)){
            return null;
        }
        User user = JsonUtils.jsonToPojo(userInfo, User.class);
        //取到用户信息，更新redis的token过期时间
        jedisClient.expire(APPLICATION_NAME+":"+TOKEN+":"+token,EXPIRE_TIME);
        //返回结果
        return user;
    }
}
