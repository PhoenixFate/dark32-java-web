package com.phoenix.service.impl;

import com.phoenix.dao.UserDao;
import com.phoenix.domain.User;
import com.phoenix.service.UserService;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDao();

    public User login(User user) {
        //打开事务
        Transaction transaction = HibernateUtils.getCurrentSession().beginTransaction();
        //1.根据用户名查询user
        User existUser = userDao.findByUserCode(user.getUserCode());
        //提交事务
        transaction.commit();

        //1.1没有用户，则抛出异常并提示 用户名不对
        if(existUser==null){
            throw new RuntimeException("用户名不存在");
        }
        //2.比对密码是否正确
        //2.1密码错误，抛出异常，密码错误
        if(!existUser.getUserPassword().equals(user.getUserPassword())){
            throw new RuntimeException("密码错误");
        }
        //3.将查询到到user返回
        return existUser;
    }
}
