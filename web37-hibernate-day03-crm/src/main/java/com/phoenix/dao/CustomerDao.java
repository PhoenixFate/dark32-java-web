package com.phoenix.dao;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class CustomerDao {

    public void save(Customer customer){
        //获得session
        Session openSession = HibernateUtils.getCurrentSession();
        //执行保存
        openSession.save(customer);
    }

    public Customer getById(Long id){
        Session currentSession = HibernateUtils.getCurrentSession();
        Customer customer = currentSession.get(Customer.class, id);
        return customer;
    }

    public List<Customer> findAllCriteria(){
        Session currentSession = HibernateUtils.getCurrentSession();
        //criteria查询全部
        Criteria criteria = currentSession.createCriteria(Customer.class);
        List<Customer> list = criteria.list();
        return list;
    }

    public List<Customer> findAllHql(){
        Session currentSession = HibernateUtils.getCurrentSession();
        String hql="from Customer";
        Query query = currentSession.createQuery(hql);
        List list = query.list();
        return list;
    }

}
