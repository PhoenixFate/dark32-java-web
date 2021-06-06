package com.phoenix.lazy;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 查询优化
 * 懒加载｜延迟加载
 */
public class OptimizeDemo {

    /**
     * 懒加载
     *
     * get 方法： 立即加载，执行方法时立即发送sql进行查询
     */
    @Test
    public void test01(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Customer customer = openSession.get(Customer.class, 11L);
        System.out.println(customer);

        transaction.commit();
        openSession.close();
    }

    /**
     * load
     *   执行时不进行查询，先返回一个对
     *   使用该对象时，执行查询
     * 延迟加载
     */
    @Test
    public void test02(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Customer customer = openSession.load(Customer.class, 11L);
        System.out.println(customer);

        transaction.commit();
        openSession.close();
    }



}
