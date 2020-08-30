package com.phoenix.dao;

import com.phoenix.domain.Customer;
import com.phoenix.domain.Linkman;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class LinkmanDao {

    public void addLinkman(Customer customer, Linkman linkman){
        Session currentSession = HibernateUtils.getCurrentSession();
        customer.getLinkmanSet().add(linkman);
        linkman.setCustomer(customer);
        currentSession.save(linkman);
    }

    public List<Linkman> getAllCriteria(){
        Session currentSession = HibernateUtils.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Linkman.class);
        List list = criteria.list();
        return list;
    }

    public List<Linkman> getAllHql(){
        Session currentSession = HibernateUtils.getCurrentSession();
        String hql="from linkman";
        Query query = currentSession.createQuery(hql);
        List list = query.list();
        return list;
    }

    public void save(Linkman linkman) {
        Session currentSession = HibernateUtils.getCurrentSession();
        currentSession.save(linkman);
    }
}
