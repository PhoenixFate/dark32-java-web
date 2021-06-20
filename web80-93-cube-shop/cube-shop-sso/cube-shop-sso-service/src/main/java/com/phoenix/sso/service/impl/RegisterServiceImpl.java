package com.phoenix.sso.service.impl;

import com.phoenix.sso.mapper.UserMapper;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.pojo.UserExample;
import com.phoenix.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean register(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(StringUtils.isBlank(user.getUsername())|| StringUtils.isBlank(user.getPhone())|| StringUtils.isBlank(user.getPassword())){
            throw new RuntimeException("用户名、密码，phone不能为空");
        }
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(user.getPhone());
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null&&users.size()>0){
            throw new RuntimeException("手机号已经存在");
        }
        UserExample userExample2=new UserExample();
        UserExample.Criteria criteria2 = userExample.createCriteria();
        criteria2.andUsernameEqualTo(user.getUsername());
        List<User> users2 = userMapper.selectByExample(userExample2);
        if(users2!=null&&users2.size()>0){
            throw new RuntimeException("用户名已经存在");
        }

        user.setCreated(new Date());
        user.setUpdated(new Date());

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes("utf-8")));
        int count = userMapper.insert(user);
        return count>0;
    }
}
