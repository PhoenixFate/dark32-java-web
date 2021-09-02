package com.phoenix.junit;

import com.phoenix.dao.UserDao;
import com.phoenix.dao.UserDaoImpl;
import com.phoenix.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 原始开发
 * 通过mybatis操作xml
 * 自己显示dao的实现类
 */
public class MybatisTest2 {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    @Test
    public void fun1() throws IOException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.selectUserById(10);
        System.out.println(user);
    }

}
