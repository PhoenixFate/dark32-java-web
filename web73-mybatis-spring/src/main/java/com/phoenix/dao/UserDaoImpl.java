package com.phoenix.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    /**
     * mybatis整合spring
     * 原始dao的开发（即使用mapper文件）
     */
    public void insertUser() {
//		this.getSqlSession().insert(arg0, arg1)
    }
}
