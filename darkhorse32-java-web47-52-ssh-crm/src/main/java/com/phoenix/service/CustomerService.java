package com.phoenix.service;

import com.phoenix.domain.Customer;
import com.phoenix.utils.PageBean;

public interface CustomerService {

    PageBean<Customer> getList(Integer currentPage,Integer pageSize,Customer customer);

}
