package com.phoenix.hql;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * 多表联合查询
 */
public class MultiTableHqlDemo {

    /**
     * 交叉连接（笛卡尔积）
     * select * from a,b
     *
     *
     * 内连接
     * （隐式内连接  select * from a,b where a.id=b.aid
     *  显示内连接） select * from a inner join b on a.id=b.aid
     *
     * 外连接
     * （左外  select * from a left [outer] join b on a.id=b.aid
     *  右外） select * from a right [outer] join b on a.id=b.aid
     *
     *  hql 多表查询也是3种
     *      内连接，内连接（迫切）
     *      左外，左外（迫切）
     *      右外，右外（迫切）
     */


    /**
     * hql
     *      内连接
     */
    @Test
    public void test01(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql="from Customer c inner join c.linkmanSet "; //返回List<String>
        Query query = openSession.createQuery(hql);
        List<Object[]> list = query.list();
        for(Object[] objects:list){
            System.out.println(Arrays.toString(objects));
        }

        transaction.commit();
        openSession.close();
    }


    /**
     * hql
     *    迫切内连接
     */
    @Test
    public void test02(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql="from Customer c inner join fetch c.linkmanSet "; //返回List<String>
        Query query = openSession.createQuery(hql);
        List<Customer> list = query.list();
        for (Customer customer:list){
            System.out.println(customer);
        }

        transaction.commit();
        openSession.close();
    }


    /**
     * hql
     *    左外连接
     */
    @Test
    public void test03(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql="from Customer c left join c.linkmanSet "; //返回List<String>
        Query query = openSession.createQuery(hql);
        List<Object[]> list = query.list();
        for(Object[] objects:list){
            System.out.println(Arrays.toString(objects));
        }

        transaction.commit();
        openSession.close();
    }


    /**
     * hql
     *    左外连接 迫切
     */
    @Test
    public void test04(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql="from Customer c left join fetch c.linkmanSet "; //返回List<String>
        Query query = openSession.createQuery(hql);
        List<Customer> list = query.list();
        for (Customer customer:list){
            System.out.println(customer);
        }

        transaction.commit();
        openSession.close();
    }

    /**
     * hql
     *    右外连接
     */
    @Test
    public void test05(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql="from Customer c right join c.linkmanSet "; //返回List<String>
        Query query = openSession.createQuery(hql);
        List<Object[]> list = query.list();
        for(Object[] objects:list){
            System.out.println(Arrays.toString(objects));
        }

        transaction.commit();
        openSession.close();
    }

    /**
     * hql
     *    右外连接 迫切
     */
    @Test
    public void test06(){
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();

        //hql
        String hql="from Customer c right join fetch c.linkmanSet "; //返回List<String>
        Query query = openSession.createQuery(hql);
        List<Customer> list = query.list();
        for (Customer customer:list){
            System.out.println(customer);
        }
        transaction.commit();
        openSession.close();
    }

}
