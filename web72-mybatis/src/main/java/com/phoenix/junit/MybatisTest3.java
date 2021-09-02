package com.phoenix.junit;

import com.phoenix.mapper.UserMapper;
import com.phoenix.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis通过UserMapper.xml动态代理实现dao
 */
public class MybatisTest3 {

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
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserById(10);
        System.out.println(user);
    }
}
