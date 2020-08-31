package com.phoenix.web;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.phoenix.domain.Customer;
import com.phoenix.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private CustomerService customerService;

    private Customer customer=new Customer();

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String list() throws Exception {
        //1.调用service，查询分页数据 PageBean
        //customerService.getList()

        //2.将pageBean放入request域中，转发到list页面显示数据
        return "list";
    }

    public Customer getModel() {
        return customer;
    }
}
