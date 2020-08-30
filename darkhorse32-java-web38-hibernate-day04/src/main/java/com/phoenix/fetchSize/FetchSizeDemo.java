package com.phoenix.fetchSize;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

public class FetchSizeDemo {

    /**
     *  抓取数量
     */
    @Test
    public void test01()
    {
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
