package com.phoenix.cirteria;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * criteria
 */
public class CriteriaDemo {

    /**
     * 条件查询
     */
    @Test
    public void test01(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Criteria criteria = openSession.createCriteria(Customer.class);
        //criteria.add(Restrictions.idEq(11L));
        criteria.add(Restrictions.eq("customerId",11L));

        Customer customer = (Customer) criteria.uniqueResult();
        System.out.println(customer);

        transaction.commit();
        openSession.close();
    }

    /**
     * 分页查询
     * 与hql一样
     */
    @Test
    public void test02(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Criteria criteria = openSession.createCriteria(Customer.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(2);

        List<Customer> list = criteria.list();
        for(Customer customer:list){
            System.out.println(list);
        }

        transaction.commit();
        openSession.close();
    }


    /**
     * 排序语法
     */
    @Test
    public void test03(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Criteria criteria = openSession.createCriteria(Customer.class);
        criteria.addOrder(Order.desc("customerId"));

        List<Customer> list = criteria.list();
        for(Customer customer:list){
            System.out.println(customer);
        }

        transaction.commit();
        openSession.close();
    }

    /**
     * 统计语法
     * count sum avg min max
     */
    @Test
    public void test04(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Criteria criteria = openSession.createCriteria(Customer.class);
        criteria.setProjection(Projections.count("customerId"));
        Long count = (Long)criteria.uniqueResult();

        System.out.println(count);
        transaction.commit();
        openSession.close();
    }



}
