package com.phoenix.hql;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * hql一般用于多表简单联合查询
 *
 * 多表复杂联合查询用 原生sql
 *
 */
public class HqlDemo {

    /**
     *  hql
     *  基本查询
     */
    @Test
    public void test01(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //hql:
        //1.书写hql语句
        // from 后面跟着的对象的完整类名，如果查询全部，可以省略select *
        //String hql="from com.phoenix.Customer";
        // 如果类名没有重复，可以省略包路径
        String hql="from Customer"; //select * from tb_customer

        //2.根据hql语句创建查询对象
        Query query = session.createQuery(hql);

        //3.根据查询对象获得结果
        List list = query.list();// list
        System.out.println(list);
        //Object object = query.uniqueResult();//唯一结果

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


    /**
     *  hql
     *  条件查询
     *  hql 中不可能出现数据库字段
     *
     */
    @Test
    public void test02(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //hql:
        //1.书写hql语句
        // from 后面跟着的对象的完整类名，如果查询全部，可以省略select *
        //String hql="from com.phoenix.Customer";
        // 如果类名没有重复，可以省略包路径
        String hql="from Customer where customerId=3"; //select * from tb_customer

        //2.根据hql语句创建查询对象
        Query query = session.createQuery(hql);

        //3.根据查询对象获得结果
        List list = query.list();// list
        System.out.println(list);
        Customer customer = (Customer) query.uniqueResult();//唯一结果
        System.out.println(customer);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


    /**
     *  hql
     *  条件查询 ?占位符
     *  hql 中不可能出现数据库字段
     *
     */
    @Test
    public void  test03(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //hql:
        //1.书写hql语句
        // from 后面跟着的对象的完整类名，如果查询全部，可以省略select *
        //String hql="from com.phoenix.Customer";
        // 如果类名没有重复，可以省略包路径
        String hql="from Customer where customerId=?"; //select * from tb_customer

        //2.根据hql语句创建查询对象
        Query query = session.createQuery(hql);
        //设置条件值
        //query.setLong(0,3L);
        query.setParameter(0,3L);

        //3.根据查询对象获得结果
        List list = query.list();// list
        System.out.println(list);
        Customer customer = (Customer) query.uniqueResult();//唯一结果
        System.out.println(customer);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


    /**
     *  hql
     *  命名占位符
     *
     */
    @Test
    public void  test04(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //hql:
        //1.书写hql语句
        // from 后面跟着的对象的完整类名，如果查询全部，可以省略select *
        //String hql="from com.phoenix.Customer";
        // 如果类名没有重复，可以省略包路径
        String hql="from Customer where customerId=:customerId"; //select * from tb_customer

        //2.根据hql语句创建查询对象
        Query query = session.createQuery(hql);
        //设置条件值
        //query.setLong(0,3L);
        query.setParameter("customerId",3L);

        //3.根据查询对象获得结果
        List list = query.list();// list
        System.out.println(list);
        Customer customer = (Customer) query.uniqueResult();//唯一结果
        System.out.println(customer);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


    /**
     *  hql
     *  分页
     *
     */
    @Test
    public void  test05(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //hql:
        //1.书写hql语句
        // from 后面跟着的对象的完整类名，如果查询全部，可以省略select *
        //String hql="from com.phoenix.Customer";
        // 如果类名没有重复，可以省略包路径
        String hql="from Customer "; //select * from tb_customer

        //2.根据hql语句创建查询对象
        Query query = session.createQuery(hql);
        //设置条件值
        //类似于limit ?,?
        query.setFirstResult(0);
        query.setMaxResults(3);

        //3.根据查询对象获得结果
        List list = query.list();// list
        System.out.println(list);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


}
