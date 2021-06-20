package com.phoenix.test;

import com.phoenix.dao.UserDao;
import com.phoenix.domain.User;
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
 * 测试hibernate框架是否配置成功
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {

    @Resource(name = "localSessionFactoryBean")
    private SessionFactory sessionFactory;

    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * 单独测试hibernate是否配置成功
     */
    @Test
    public void fun1() {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setUserCode("xxx");
        user.setUserName("67");
        user.setUserPassword("123456");
        user.setUserState('Y');
        session.save(user);
        transaction.commit();
        session.close();
    }

    /**
     * 测试spring配置hibernate
     */
    @Test
    public void fun2() {
        Session session = sessionFactory.openSession();

        User user = new User();
        user.setUserCode("test222");
        user.setUserName("222");
        user.setUserPassword("123456");
        user.setUserState('Y');
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }


    /**
     * 测试Dao hibernate模板
     */
    @Test
    public void fun3() {
        User tom = userDao.getUserByCode("tom");
        System.out.println(tom);

        User tom2 = userDao.getUserByCode2("tom");
        System.out.println(tom2);
    }

}
