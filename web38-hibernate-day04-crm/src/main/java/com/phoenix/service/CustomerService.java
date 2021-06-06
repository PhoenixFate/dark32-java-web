package com.phoenix.service;

import com.phoenix.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerService {

    Customer getById(Long id);

    void save(Customer customer);

    List<Customer> findAllCriteria();

    List<Customer> findAllHql();

    //根据条件进行查询
    List<Customer> findAllCriteria(DetachedCriteria detachedCriteria);
}
