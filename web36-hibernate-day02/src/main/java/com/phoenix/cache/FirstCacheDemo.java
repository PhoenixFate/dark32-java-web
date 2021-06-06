package com.phoenix.cache;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * hibernate 一级缓存原理
 */
public class FirstCacheDemo {

    @Test
    public void test01(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作

        Customer customer1 = session.get(Customer.class, 2L);
        Customer customer2 = session.get(Customer.class, 2L);
        Customer customer3 = session.get(Customer.class, 2L);
        Customer customer4 = session.get(Customer.class, 2L);
        Customer customer5 = session.get(Customer.class, 2L);

        System.out.println(customer1==customer2);
        System.out.println(customer3==customer4);


        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }

    @Test
    public void test02(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作

        Customer customer1 = session.get(Customer.class, 2L);
        customer1.setCustomerName("new name");



        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


    /**
     * 缓存进阶之快照
     */
    @Test
    public void test03(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作

        Customer customer1 = session.get(Customer.class, 2L);
        //最开始的Customer1的name是"new name"

        customer1.setCustomerName("哈哈");
        customer1.setCustomerName("new name");


        //4.提交事务
        transaction.commit();

        //这时候的customer1会跟缓存中的快照比对，相关，则不执行update操作

        //5.关闭资源
        session.close();
    }



}
