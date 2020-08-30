package com.phoenix.transaction;


import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

/**
 * sessionFactory.getCurrentSession()
 */
public class TransactionDemo {


    @Test
    public void test01(){
        Session currentSession1 = HibernateUtils.getCurrentSession();
        Session currentSession2 = HibernateUtils.getCurrentSession();
        Session currentSession3 = HibernateUtils.getCurrentSession();

        System.out.println(currentSession1==currentSession2);
        System.out.println(currentSession2==currentSession3);


        /*
         * 通过getCurrentSession获得的session，当事务提交时候，session会自动关闭，需不要调用close关闭
         *
         */

    }


    @Test
    public void test02(){
        Session openSession1 = HibernateUtils.getOpenSession();
        Session openSession2 = HibernateUtils.getOpenSession();
        System.out.println(openSession1==openSession2);
    }

}
