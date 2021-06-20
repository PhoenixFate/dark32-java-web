package com.phoenix.sso.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONObject;
import com.phoenix.common.jedis.JedisClient;
import com.phoenix.common.utils.JsonUtils;
import com.phoenix.sso.mapper.UserMapper;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.pojo.UserExample;
import com.phoenix.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${APPLICATION_NAME}")
    private String APPLICATION_NAME;

    @Value("${TOKEN}")
    private String TOKEN;

    @Value("${EXPIRE_TIME}")
    private Integer EXPIRE_TIME;

    @Override
    public String login(String username, String password) throws IOException {
        //1.判断用户名密码是否正确
        //2.如果不正确，返回登录失败
        //3.如果正确，生成token
        //4.把用户信息写入token； key:token, value:用户信息
        //5.设置session过期时间
        //6.返回token


        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes("utf-8")));
        List<User> users = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(users)){
            //登录失败
            return null;
        }else {
            //登录成功
            User user=users.get(0);
            user.setPassword("");
            //生成token
            String token= UUID.randomUUID().toString();
            jedisClient.set(APPLICATION_NAME+":"+TOKEN+":"+token, JsonUtils.objectToJson(user));
            jedisClient.expire(APPLICATION_NAME+":"+TOKEN+":"+token,EXPIRE_TIME);
            return token;
        }
    }
}
