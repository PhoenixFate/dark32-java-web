package com.phoenix.service.impl;

import com.phoenix.dao.CustomerDao;
import com.phoenix.domain.Customer;
import com.phoenix.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao=new CustomerDao();

    public void save(Customer customer) {
        customerDao.save(customer);
    }

}
