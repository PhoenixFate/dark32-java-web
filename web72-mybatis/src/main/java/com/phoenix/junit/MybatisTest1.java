package com.phoenix.junit;

import com.phoenix.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
  mybatis直接操作*.xml
 */
public class MybatisTest1 {

    @Test
    public void fun1() throws IOException {
        //加载核心配置文件
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行Sql语句
        User user = sqlSession.selectOne("test.findUserById", 10);
        System.out.println(user);
    }

    @Test
    public void fun2() throws IOException {
        //加载核心配置文件
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行Sql语句
        List<User> user = sqlSession.selectList("test.findUserByUsername", "五");
        System.out.println(user);
    }


    @Test
    public void fun3() throws IOException {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user=new User();
        user.setBirthday(new Date());
        user.setUsername("测试");
        user.setSex("男");
        //执行Sql语句
        int count = sqlSession.insert("test.insertUser", user);
        System.out.println(count);
        System.out.println(user);
        sqlSession.commit();
        System.out.println(user);
    }

}
