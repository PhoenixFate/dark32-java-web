package com.phoenix.cirteria;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;

/**
 * 离线criteria
 */
public class OfflineCriteriaDemo {

    @Test
    public void test01(){
        //先创建Criteria
        //service层或者web层创建Criteria
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
        detachedCriteria.add(Restrictions.eq("customerId",11L));

        //-------------dao 层
        Session openSession = HibernateUtils.getOpenSession();
        Transaction transaction = openSession.beginTransaction();
        Criteria criteria = detachedCriteria.getExecutableCriteria(openSession);
        List list = criteria.list();
        System.out.println(list);

        transaction.commit();
        openSession.close();
    }








}
