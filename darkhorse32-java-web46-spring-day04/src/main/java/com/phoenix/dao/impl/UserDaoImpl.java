package com.phoenix.dao.impl;

import com.phoenix.dao.UserDao;
import com.phoenix.domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * HibernateDaoSupport 需要sessionFactory
 * 所以需要为UserDaoImpl注入 sessionFactory
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    private HibernateTemplate hibernateTemplate;

    public User getUserByCode(final String userCode) {
        //HQL
        User user = getHibernateTemplate().execute(new HibernateCallback<User>() {
            public User doInHibernate(Session session) throws HibernateException {
                String hql = "from User where userCode=?";
                Query query = session.createQuery(hql);
                query.setParameter(0, userCode);
                User user = (User) query.uniqueResult();
                return user;
            }
        });
        return user;
    }

    public User getUserByCode2(final String userCode) {
        //criteria
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("userCode",userCode));
        List<User> list = (List<User>)getHibernateTemplate().findByCriteria(detachedCriteria);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

}
