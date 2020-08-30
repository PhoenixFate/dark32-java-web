package com.phoenix.state;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * hibernate 对象状态
 *
 * 对象有三种状态：
 * 瞬时状态： 没有id，没有与session关联
 * 持久化状态 有id，与session关联
 * 游离|托管状态 有id，没有与session关联
 *
 */
public class ObjectStatusDemo {

    /**
     * 测试对象的三种状态
     */
    @Test
    public void test01(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        Customer customer=new Customer();//没有id，没有与session关联 => 瞬时状态
        //如果是主键自增策略，设置id无意义
        //customer.setCustomerId(100L);
        customer.setCustomerName("主键自增生成策略"); //瞬时状态

        session.save(customer);//持久化状态, 有id
        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();

        //对象的状态变成 游离|托管状态
    }


    /**
     * session.save 将瞬时状态转换为持久化状态
     * 主键自增；执行save方法时，为了将对象转换为持久化状态，必须生成id值，所以需要insert插入数据；
     */
    @Test
    public void test02(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        Customer customer=new Customer();//没有id，没有与session关联 => 瞬时状态
        //如果是主键自增策略，设置id无意义
        //customer.setCustomerId(100L);
        customer.setCustomerName("主键自增生成策略"); //瞬时状态

        session.save(customer);//持久化状态, 有id
        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();

        //对象的状态变成 游离|托管状态
    }


}
