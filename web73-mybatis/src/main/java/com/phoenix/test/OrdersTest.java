package com.phoenix.test;

import com.phoenix.mapper.OrderMapper;
import com.phoenix.pojo.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrdersTest {

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
     * 订单查询
     */
    @Test
    public void fun1() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        List<Orders> allOrdersList = orderMapper.getAllOrderList();
        System.out.println(allOrdersList);
    }

    /**
     * 订单一对一关联查询
     */
    @Test
    public void fun5() {
        //创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> allOrdersList = orderMapper.selectOrdersWithUser();
        System.out.println(allOrdersList);
    }



}
