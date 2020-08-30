package com.phoenix.hql;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

public class HqlDemo {

    /**
     * 基本语法
     */
    @Test
    public void test01(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql1="from com.phoenix.Customer";//完整写法
        String hql2="from Customer";//简单写法 要求Customer唯一
        String hql3="from Object";
        Query query = openSession.createQuery(hql2);
        List<Customer> list = query.list();
        for(Customer customer:list){
            System.out.println(customer);
        }

        transaction.commit();
        openSession.close();
    }

    /**
     * 排序语法
     */
    @Test
    public void test02(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql1="from Customer order by customerId asc";
        String hql2="from Customer order by customerId desc";
        Query query = openSession.createQuery(hql2);
        List<Customer> list = query.list();
        for(Customer customer:list){
            System.out.println(customer);
        }

        transaction.commit();
        openSession.close();
    }

    /**
     * 条件查询
     */
    @Test
    public void test03(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql1="from Customer order where customerId=?";
        String hql2="from Customer order where customerId=:id";
        Query query1 = openSession.createQuery(hql1);
        query1.setParameter(0,6L);
        Customer customer1 = (Customer) query1.uniqueResult();
        System.out.println(customer1);

        Query query2 = openSession.createQuery(hql2);
        query2.setParameter("id",5L);
        Customer customer2=(Customer) query2.uniqueResult();
        System.out.println(customer2);

        transaction.commit();
        openSession.close();
    }


    /**
     * 分页查询
     */
    @Test
    public void test04(){
        // limit ?,?
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql="from Customer";
        Query query = openSession.createQuery(hql);
        query.setFirstResult(2);
        query.setMaxResults(2);
        List<Customer> list = query.list();
        for(Customer customer:list){
            System.out.println(customer);
        }

        transaction.commit();
        openSession.close();
    }


    /**
     * 统计查询
     * count
     * sum
     * svg
     * max
     * min
     */
    @Test
    public void test05(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //count()
        String hql="select count(*) from Customer";
        Query query1 = openSession.createQuery(hql);
        Number count = (Number) query1.uniqueResult();
        System.out.println(count);

        //sum()
        String sumHql="select sum(customerId) from Customer";
        Query query2=openSession.createQuery(sumHql);
        Number sum=(Number) query2.uniqueResult();
        System.out.println(sum);

        //avg()
        String avgHql="select avg(customerId) from Customer";
        Query query3=openSession.createQuery(avgHql);
        Number avg=(Number) query3.uniqueResult();
        System.out.println(avg);

        //min()
        String minHql="select min(customerId) from Customer";
        Query query4=openSession.createQuery(minHql);
        Number min=(Number) query4.uniqueResult();
        System.out.println(min);

        //max()
        String maxHql="select max(customerId) from Customer";
        Query query5=openSession.createQuery(maxHql);
        Number max=(Number) query5.uniqueResult();
        System.out.println(max);

        transaction.commit();
        openSession.close();
    }

    /**
     * 投影查询
     */
    @Test
    public void test06(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql="select customerName from Customer"; //返回List<String>
        String hql2="select customerName,customerId from Customer"; //返回List<Object[]>
        String hql3="select new Customer(customerId,customerName) from Customer";//返回List<Customer>
        Query query = openSession.createQuery(hql3);
        List<Customer> list=query.list();
        for(Customer customer:list){
            System.out.println(customer);
        }

        transaction.commit();
        openSession.close();
    }
}
