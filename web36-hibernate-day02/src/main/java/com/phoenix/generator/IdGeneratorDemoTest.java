package com.phoenix.generator;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class IdGeneratorDemoTest {

    /**
     * 主键生成策略
     */
    @Test
    public void test01(){
        //1.获得session
        Session session = HibernateUtils.getOpenSession();

        //2.控制事务
        Transaction transaction = session.beginTransaction();
        //3.执行操作
        Customer customer=new Customer();
        //如果是主键自增策略，设置id无意义
        //customer.setCustomerId(100L);
        customer.setCustomerName("主键自增生成策略");

        session.save(customer);
        //4.提交事务
        transaction.commit();
        //5.关闭资源
        session.close();
    }



}
