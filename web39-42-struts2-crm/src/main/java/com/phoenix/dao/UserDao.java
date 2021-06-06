package com.phoenix.dao;

import com.phoenix.domain.User;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;

public class UserDao  {

    public User findByUserCode(String userCode){
        Session currentSession = HibernateUtils.getCurrentSession();
        //hql查询
        //书写hql语句
        String hql="from User where userCode=?";
        //创建查询对象
        Query query = currentSession.createQuery(hql);
        //设置参数
        query.setParameter(0,userCode);
        //执行查询
        User user = (User)query.uniqueResult();
        return user;
    }

}
