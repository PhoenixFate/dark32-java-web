package com.phoenix.service.impl;

import com.phoenix.dao.UserDao;
import com.phoenix.domain.User;
import com.phoenix.service.UserService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl implements UserService {

    private UserDao ud;

    public User getUserByCodePassword(User user) {
        //1 根据登陆名称查询登陆用户
        User existU = ud.getUserByCode(user.getUserCode());
        //2 判断用户是否存在.不存在=>抛出异常,提示用户名不存在
        if (existU == null) {
            throw new RuntimeException("用户名不存在!");
        }
        //3 判断用户密码是否正确=>不正确=>抛出异常,提示密码错误
        if (!existU.getUserPassword().equals(user.getUserPassword())) {
            throw new RuntimeException("密码错误!");
        }
        //4 返回查询到的用户对象
        return existU;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveUser(User u) {
        ud.save(u);
    }

    public void setUd(UserDao ud) {
        this.ud = ud;
    }
}
