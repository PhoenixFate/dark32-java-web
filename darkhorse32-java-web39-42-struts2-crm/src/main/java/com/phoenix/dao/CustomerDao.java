package com.phoenix.dao;

import com.phoenix.domain.Customer;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

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

    /**
     * 根据条件查询所有
     * @param detachedCriteria
     * @return
     */
    public List<Customer> findAll(DetachedCriteria detachedCriteria) {
        //1.获得session
        Session currentSession = HibernateUtils.getCurrentSession();
        //2.将离线对象关联到session
        Criteria executableCriteria = detachedCriteria.getExecutableCriteria(currentSession);
        List list = executableCriteria.list();
        return list;
    }
}
