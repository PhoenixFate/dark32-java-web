package com.phoenix.lazyFetch;

import com.phoenix.domain.Customer;
import com.phoenix.domain.Linkman;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * 关联级别 延迟加载&抓取策略
 */
public class LazyFetchDemo {

    /**
     * 关联级别
     * 集合级别对关联
     *
     * lazy:true
     * fetch:select(但表查询) 默认值
     * 使用时，才加载linkman 集合
     **/
    @Test
    public void test01(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();
        Customer customer = openSession.get(Customer.class, 11L);
        System.out.println(customer);
        //关联级别
        Set<Linkman> linkmanSet =  customer.getLinkmanSet();
        System.out.println(linkmanSet);
        transaction.commit();
        openSession.close();
    }

    /**
     * 关联级别
     * 集合级别对关联
     *
     * lazy:false
     * fetch:select(但表查询) 默认值
     *
     * 立即加载linkman set集合
     **/
    @Test
    public void test02(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();
        Customer customer = openSession.get(Customer.class, 11L);
        System.out.println(customer);
        //关联级别
        Set<Linkman> linkmanSet =  customer.getLinkmanSet();
        System.out.println(linkmanSet);
        transaction.commit();
        openSession.close();
    }

    /**
     * 关联级别
     * 集合级别对关联
     *
     * lazy:extra
     * fetch:select(但表查询) 默认值
     *
     * 使用时set.size 的时候，只查询count（*），使用整个set集合才查询
     **/
    @Test
    public void test03(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();
        Customer customer = openSession.get(Customer.class, 11L);
        System.out.println(customer);
        //关联级别
        Set<Linkman> linkmanSet =  customer.getLinkmanSet();
        System.out.println(linkmanSet.size());
        System.out.println(linkmanSet);
        transaction.commit();
        openSession.close();
    }


    /**
     * 关联级别
     * 集合级别对关联
     *
     * lazy:true｜false｜extra 熟悉失效 立即加载
     * fetch:join 多表查询
     *
     * 多表查询 left join 查询customer的时候就把linkman查询出来
     **/
    @Test
    public void test04(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();
        Customer customer = openSession.get(Customer.class, 11L);
        System.out.println(customer);
        //关联级别
        Set<Linkman> linkmanSet =  customer.getLinkmanSet();
        System.out.println(linkmanSet.size());
        System.out.println(linkmanSet);
        transaction.commit();
        openSession.close();
    }

    /**
     * 关联级别
     * 集合级别对关联
     *
     * lazy:true
     * fetch:subselect  子查询
     *
     **/
    @Test
    public void test05(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        String hql="from Customer";
        Query query = openSession.createQuery(hql);
        List<Customer> list = query.list();
        for(Customer customer:list){
            System.out.println(customer);
            System.out.println(customer.getLinkmanSet().size());
        }

        transaction.commit();
        openSession.close();
    }


    /**
     * 关联级别
     * 集合级别对关联
     *
     * lazy:false
     * fetch:subselect  子查询
     *
     **/
    @Test
    public void test06(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        String hql="from Customer";
        Query query = openSession.createQuery(hql);
        List<Customer> list = query.list();
        for(Customer customer:list){
            System.out.println(customer);
            System.out.println(customer.getLinkmanSet().size());
            System.out.println(customer.getLinkmanSet());
        }

        transaction.commit();
        openSession.close();
    }


    /**
     * 关联级别
     * 集合级别对关联
     *
     * lazy:extra
     * fetch:subselect  子查询
     *
     **/
    @Test
    public void test07(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        String hql="from Customer";
        Query query = openSession.createQuery(hql);
        List<Customer> list = query.list();
        for(Customer customer:list){
            System.out.println(customer);
            System.out.println(customer.getLinkmanSet().size());
            System.out.println(customer.getLinkmanSet());
        }

        transaction.commit();
        openSession.close();
    }
}
