package com.phoenix.lazyFetch;

import com.phoenix.domain.Linkman;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 关联级别 延迟加载&抓取策略
 *
 * 属性关联策略
 */
public class LazyFetchDemo2 {

    /**
     * 关联级别
     * 集合级别对关联
     * 反过来的关联查询
     *
     * linkman
     *      lazy:proxy
     *          customer-lazy:true
     *      fetch:select
     *
     *
     **/
    @Test
    public void test01(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Linkman linkman = openSession.get(Linkman.class, 11L);
        System.out.println(linkman.getCustomer());

        transaction.commit();
        openSession.close();
    }

    /**
     * 关联级别
     * 集合级别对关联
     * 反过来的关联查询
     *
     * linkman
     *      lazy:proxy
     *          customer-lazy:false
     *      fetch:select
     *
     *
     **/
    @Test
    public void test02(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Linkman linkman = openSession.get(Linkman.class, 11L);
        System.out.println(linkman.getCustomer());

        transaction.commit();
        openSession.close();
    }


    /**
     * 关联级别
     * 集合级别对关联
     * 反过来的关联查询
     *
     * linkman
     *      lazy:失效
     *      fetch:join 多表查询
     *
     *
     **/
    @Test
    public void test03(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        Linkman linkman = openSession.get(Linkman.class, 11L);
        System.out.println(linkman.getCustomer());

        transaction.commit();
        openSession.close();
    }


}
