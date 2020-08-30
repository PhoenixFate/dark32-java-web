package com.phoenix.utils;

import com.phoenix.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    /**
     * 一个web项目 只创建一个sessionFactory
     */
    static{
        //1.创建，调用空参构造
        Configuration configuration=new Configuration().configure();
        //2.根据配置信息，创建SesssionFactory对象
        sessionFactory=configuration.buildSessionFactory();
    }

    /**
     * 获得全新session
     * @return
     */
    public static Session getOpenSession(){
        //3.获得session
        Session session = sessionFactory.openSession();
        return session;
    }

    /**
     *  获得当前线程的session
     * @return
     */
    public static Session getCurrentSession(){
        //3.获得session
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession;
    }

    /**
     * 测试工具类
     */
    public static void main(String[] args) {
        Session openSession = getOpenSession();
        Customer customer = openSession.get(Customer.class, 2L);
        System.out.println(customer);
    }

}
