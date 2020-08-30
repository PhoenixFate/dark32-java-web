package com.phoenix.test;

import com.phoenix.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void saveCustomer(){
        //1.书写configuration
        Configuration configuration=new Configuration().configure();

        SessionFactory sessionFactory=configuration.buildSessionFactory();

        Session session=sessionFactory.openSession();

        Transaction transaction=session.beginTransaction();
        /**
         * hibernate save customer
         */
        Customer customer=new Customer();
        customer.setCustomerName("南京智慧医疗");
        customer.setCustomerIndustry("医疗");
        customer.setCustomerLevel("1");
        customer.setCustomerMobile("18751801512");
        customer.setCustomerPhone("18751801512");
        customer.setCustomerLinkman("linkman");
        session.save(customer);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
