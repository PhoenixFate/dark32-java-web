package com.phoenix.service;

import com.phoenix.domain.Customer;
import com.phoenix.domain.Linkman;

import java.util.List;

public interface LinkmanService {

    void save(Linkman linkman,Long customerId);

    void addLinkman(Customer customer, Linkman linkman);

    List<Linkman> findAll();

}
