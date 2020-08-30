package com.phoenix.sql;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * hibernate 中原生sql查询
 *
 */
public class NativeSqlDemo {

    @Test
    public void test01(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //3.1书写sql
        String sql="select * from cst_customer";
        //3.2创建sql查询对象
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        //3.3指定将结果集封装到那个实体
        sqlQuery.addEntity(Customer.class);
        List<Customer> list = sqlQuery.list();
        for(Customer customer:list){
            System.out.println(customer);
        }
        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }


    /**
     * 条件查询
     */
    @Test
    public void test02(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //3.1书写sql
        String sql="select * from cst_customer where cust_id=?";
        //3.2创建sql查询对象
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0,2L);
        //3.3指定将结果集封装到那个实体
        sqlQuery.addEntity(Customer.class);
        Customer customer = (Customer) sqlQuery.uniqueResult();
        System.out.println(customer);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }

    /**
     * 分页查询
     */
    @Test
    public void test03(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        //3.1书写sql
        String sql="select * from cst_customer limit ?,?";
        //3.2创建sql查询对象
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0,1);
        sqlQuery.setParameter(1,2);
        //3.3指定将结果集封装到那个实体
        sqlQuery.addEntity(Customer.class);
        List list = sqlQuery.list();
        System.out.println(list);

        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }
}
