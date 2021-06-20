package com.phoenix.sso.service.impl;

import com.phoenix.sso.mapper.UserMapper;
import com.phoenix.sso.pojo.User;
import com.phoenix.sso.pojo.UserExample;
import com.phoenix.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean checkUsername(String username) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null&&users.size()>0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkEmail(String email) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null&&users.size()>0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkPhone(String phone) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null&&users.size()>0){
            return false;
        }
        return true;
    }
}

