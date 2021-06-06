package com.phoenix.test;

import com.phoenix.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * 测试hibernate框架是否配置成功
 */
public class HibernateTest {

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

}
