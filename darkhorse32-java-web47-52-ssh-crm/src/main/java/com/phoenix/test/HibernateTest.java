package com.phoenix.test;

import com.phoenix.dao.UserDao;
import com.phoenix.domain.User;
import com.phoenix.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 测试hibernate框架
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {

    @Resource(name = "localSessionFactoryBean2")
    private SessionFactory sessionFactory2;

    @Resource(name = "localSessionFactoryBean")
    private SessionFactory sessionFactory;

    /**
     * 单独测试hibernate
     */
    @Test
    public void test01(){
        Configuration configuration=new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user=new User();
        user.setUserCode("tom");
        user.setUserName("tom");
        user.setUserPassword("123");
        user.setUserState('1');
        session.save(user);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    /**
     * 测试spring管理的sessionFactory
     */
    @Test
    public void test02(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user=new User();
        user.setUserCode("tom2");
        user.setUserName("tom2");
        user.setUserPassword("123");
        user.setUserState('1');
        session.save(user);
        transaction.commit();
        session.close();
    }

    /**
     * 测试spring管理的sessionFactory
     */
    @Test
    public void test03(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user=new User();
        user.setUserCode("tom3");
        user.setUserName("tom3");
        user.setUserPassword("123");
        user.setUserState('1');
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Resource(name = "userDao")
    private UserDao userDao;
    /**
     * 测试hibernate模板
     */
    @Test
    public void test05(){
        User tom = userDao.getUserByCode("tom");
        System.out.println(tom);
        User tom2 = userDao.getUserByCode2("tom2");
        System.out.println(tom2);
    }

    @Resource(name = "userService")
    private UserService userService;
    /**
     * 测试service aop事务
     */
    @Test
    public void test06(){
        User user=new User();
        user.setUserState('1');
        user.setUserCode("service");
        user.setUserName("service");
        user.setUserPassword("123");
        userService.save(user);
    }

    /**
     * 测试service aop事务
     */
    @Test
    public void test07(){
        User user=new User();
        user.setUserState('1');
        user.setUserCode("service2");
        user.setUserName("service2");
        user.setUserPassword("123");
        userService.mySave(user);
    }



}
