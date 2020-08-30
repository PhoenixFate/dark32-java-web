package com.phoenix.service.impl;

import com.phoenix.dao.CustomerDao;
import com.phoenix.dao.LinkmanDao;
import com.phoenix.domain.Customer;
import com.phoenix.domain.Linkman;
import com.phoenix.service.LinkmanService;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LinkmanServiceImpl implements LinkmanService {

    private LinkmanDao linkmanDao=new LinkmanDao();

    private CustomerDao customerDao=new CustomerDao();

    public void save(Linkman linkman, Long customerId) {
        Session currentSession = HibernateUtils.getCurrentSession();
        //在service层管理事务
        Transaction transaction = currentSession.beginTransaction();
        try {
            //dao层报错，回滚
            Customer customer = customerDao.getById(customerId);
            linkman.setCustomer(customer);
            linkmanDao.save(linkman);
        } catch (Exception e){
            transaction.rollback();
        }
        //提交事务
        transaction.commit();
    }

    public void addLinkman(Customer customer, Linkman linkman) {
        linkmanDao.addLinkman(customer,linkman);
    }

    public List<Linkman> findAll() {
        List<Linkman> linkmanList = linkmanDao.getAllCriteria();
        return linkmanList;
    }

}
