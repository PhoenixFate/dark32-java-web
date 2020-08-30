package com.phoenix.dao;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDao {

    public void save(Customer customer){
        //获得session
        Session openSession = HibernateUtils.getOpenSession();

        //打开事务
        Transaction transaction = openSession.beginTransaction();

        //执行保存
        openSession.save(customer);

        //提交事务
        transaction.commit();

        //关闭资源
        openSession.close();

    }
}
