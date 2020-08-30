package com.phoenix.criteria;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * criteria 一般用于单表查询
 */
public class CriteriaDemo {

    /**
     * criteria
     * 基本查询
     */
    @Test
    public void test01(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //查询所有的Customer对象
        Criteria criteria = session.createCriteria(Customer.class);
        List list = criteria.list();
        System.out.println(list);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


    /**
     * criteria
     * 条件查询
     *
     */
    @Test
    public void test02(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //查询所有的Customer对象
        Criteria criteria = session.createCriteria(Customer.class);
        //添加查询参数
        criteria.add(Restrictions.eq("customerId",3L));
        Customer customer = (Customer) criteria.uniqueResult();
        System.out.println(customer);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }

    /**
     * criteria
     * 分页查询
     *
     */
    @Test
    public void test03(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //查询所有的Customer对象
        Criteria criteria = session.createCriteria(Customer.class);
        //设置分页信息
        criteria.setFirstResult(2);
        criteria.setMaxResults(2);
        List list = criteria.list();
        System.out.println(list);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }



    /**
     * criteria
     * 总条数
     *
     */
    @Test
    public void test04(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //查询所有的Customer对象
        Criteria criteria = session.createCriteria(Customer.class);
        //设置聚合函数
        criteria.setProjection(Projections.rowCount());
        //设置分页信息
        Long count = (Long) criteria.uniqueResult();
        System.out.println(count);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


}
