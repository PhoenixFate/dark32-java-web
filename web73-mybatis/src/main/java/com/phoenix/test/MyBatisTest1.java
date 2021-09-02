package com.phoenix.test;

import com.phoenix.mapper.UserMapper;
import com.phoenix.pojo.User;
import com.phoenix.vo.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisTest1 {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        //加载核心配置文件
        String resource = "sqlMapConfig.xml";
        InputStream in = Resources.getResourceAsStream(resource);
        //创建SqlSessionFactory
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }


    /**
     * 输入类型为包装类
     */
    @Test
    public void fun1() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo userQueryVo=new UserQueryVo();
        User user=new User();
        user.setUsername("五");
        userQueryVo.setUser(user);
        List<User> userList = userMapper.findUserByQueryVo(userQueryVo);
        System.out.println(userList);
    }

    /**
     * 输出类型为简单类型
     */
    @Test
    public void fun2() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer userCount = userMapper.getUserCount();
        System.out.println(userCount);
    }


    /**
     * if 标签
     */
    @Test
    public void fun3() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user=new User();
        user.setSex("2");
        List<User> userList = userMapper.getOrdersBySexAndUsername(user);
        System.out.println(userList);
    }

    /**
     * foreach标签
     */
    @Test
    public void fun4() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo userQueryVo=new UserQueryVo();
        List<Integer> ids=new ArrayList<>();
        ids.add(24);
        ids.add(25);
        userQueryVo.setIdList(ids);
        List<User> userList = userMapper.selectUserByIds3(userQueryVo);
        System.out.println(userList);
    }


    /**
     * foreach标签
     */
    @Test
    public void fun5() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Integer> ids=new ArrayList<>();
        ids.add(24);
        ids.add(25);
        List<User> userList = userMapper.selectUserByIds2(ids);
        System.out.println(userList);
    }

    /**
     * foreach标签
     */
    @Test
    public void fun6() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Integer[] ids=new Integer[]{24,25};
        List<User> userList = userMapper.selectUserByIds1(ids);
        System.out.println(userList);
    }

    /**
     * 一对多关联查询
     */
    @Test
    public void fun8() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.selectUserListWithOrders();
        for(User temp:userList){
            System.out.println(temp);
        }
        System.out.println(userList);
    }
}
