package com.phoenix.service;

import com.phoenix.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer getById(Long id);

    void save(Customer customer);

    List<Customer> findAllCriteria();

    List<Customer> findAllHql();
}
