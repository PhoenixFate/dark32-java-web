package com.phoenix.api;

import com.phoenix.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Session 学习
 *
 * session功能对象: 表达hibernate框架与数据库之间的连接（会话）
 *                 类似于jdbc时代的connection对象，但可以对数据库进行增删改查操作，jdbc不行
 *                 session是hibernate操作数据库的核心对象
 *
 */
public class SessionDemo {


    @Test
    public void fun1(){
        //1.创建，调用空参构造
        Configuration configuration=new Configuration().configure();
        //2.根据配置信息，创建SesssionFactory对象
        SessionFactory sessionFactory=configuration.buildSessionFactory();

        //3.获得session
        Session session = sessionFactory.openSession();
        //获得一个于线程绑定的session
        //Session currentSession = sessionFactory.getCurrentSession();

        //4.session获得数据库事务transaction对象
        //Transaction transaction = session.getTransaction();
        //获得transaction并且开启事务
        Transaction transaction = session.beginTransaction();


        //session操作数据库
        Customer customer=new Customer();
        customer.setCustomerName("session 学习");
        session.save(customer);


        //事务常用操作
        //transaction.commit();
        //transaction.rollback();


        transaction.commit();
        //是否资源
        session.close();
        sessionFactory.close();
    }


    /**
     * 修改id为1的customer的name
     */
    @Test
    public void fun2(){
        //session查询

        //1.创建，调用空参构造
        Configuration configuration=new Configuration().configure();
        //2.根据配置信息，创建SesssionFactory对象
        SessionFactory sessionFactory=configuration.buildSessionFactory();

        //3.获得session
        Session session = sessionFactory.openSession();
        //获得一个于线程绑定的session
        //Session currentSession = sessionFactory.getCurrentSession();

        //4.session获得数据库事务transaction对象
        //Transaction transaction = session.getTransaction();
        //获得transaction并且开启事务
        Transaction transaction = session.beginTransaction();
        //session操作数据库
        //查询id为1的customer对象
        //1.获得要修改的对象
        Customer customer = session.get(Customer.class, 1L);
        //2.修改
        customer.setCustomerName("session update");
        //3.执行update
        session.update(customer);
        System.out.println(customer);

        //事务常用操作
        //transaction.commit();
        //transaction.rollback();
        transaction.commit();
        //是否资源
        session.close();
        sessionFactory.close();
    }


    /**
     * 删除
     */
    @Test
    public void fun3(){
        //session查询

        //1.创建，调用空参构造
        Configuration configuration=new Configuration().configure();
        //2.根据配置信息，创建SesssionFactory对象
        SessionFactory sessionFactory=configuration.buildSessionFactory();

        //3.获得session
        Session session = sessionFactory.openSession();
        //获得一个于线程绑定的session
        //Session currentSession = sessionFactory.getCurrentSession();

        //4.session获得数据库事务transaction对象
        //Transaction transaction = session.getTransaction();
        //获得transaction并且开启事务
        Transaction transaction = session.beginTransaction();
        //session操作数据库
        //查询id为1的customer对象
        //1.获得要删除的对象
        Customer customer = session.get(Customer.class, 1L);
        //2.删除
        session.delete(customer);

        //事务常用操作
        //transaction.commit();
        //transaction.rollback();
        transaction.commit();
        //是否资源
        session.close();
        sessionFactory.close();
    }
}
