package com.phoenix.service.impl;

import com.phoenix.dao.CustomerDao;
import com.phoenix.domain.Customer;
import com.phoenix.service.CustomerService;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao=new CustomerDao();

    public void save(Customer customer) {
        Session currentSession = HibernateUtils.getCurrentSession();
        //在service层管理事务
        Transaction transaction = currentSession.beginTransaction();
        try {
            //dao层报错，回滚
            customerDao.save(customer);
        } catch (Exception e){
            transaction.rollback();
        }
        //提交事务
        transaction.commit();
    }
    
    public List<Customer> findAllCriteria(){
        List<Customer> customerList = customerDao.findAllCriteria();
        return customerList;
    }

    public List<Customer> findAllHql(){
        List<Customer> customerList = customerDao.findAllHql();
        return customerList;
    }

}
