package com.phoenix.service.impl;

import com.phoenix.dao.UserDao;
import com.phoenix.domain.User;
import com.phoenix.service.UserService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,readOnly = false)
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public User getUserByCodePassword(User user) {
        System.out.println("getUserByCodePassword");
        //User loginUser = userDao.getUserByCodePassword(user);
        //1.根据用户名查询用户
        User userByCode = userDao.getUserByCode(user.getUserCode());
        //2.判断用户是否存在。不存在=>抛出异常，提示用户名不存在
        if(userByCode==null){
            throw new RuntimeException("用户名不存在");
        }
        //3.判断密码是否正确，不正确，抛出异常，提示密码错误
        if(!userByCode.getUserPassword().equals(user.getUserPassword())){
            throw new RuntimeException("密码错误");
        }
        //4.返回到的用户对象
        return userByCode;
    }

    public Integer save(User user) {
        Integer count = userDao.save(user);
        return count;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,readOnly = false)
    public Integer mySave(User user) {
        Integer count = userDao.save(user);
        return count;
    }

    public User login(User user) {


        return null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
