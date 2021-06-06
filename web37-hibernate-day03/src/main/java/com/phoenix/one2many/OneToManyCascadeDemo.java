package com.phoenix.one2many;

import com.phoenix.domain.Customer;
import com.phoenix.domain.Linkman;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 一对多，多对一操作
 *
 * 级联操作
 *
 */
public class OneToManyCascadeDemo {

    @Test
    //1对多 添加
    public void test01(){
        //1.获得session
        Session openSession = HibernateUtils.getOpenSession();

        //2.获得事务
        Transaction transaction = openSession.beginTransaction();

        //3.操作
        Customer customer=new Customer();
        customer.setCustomerName("oneToMany cascade");

        Linkman linkman1=new Linkman();
        linkman1.setName("o2m linkman1 cascade");
        Linkman linkman2=new Linkman();
        linkman2.setName("o2m linkman2 cascade");

        //表达1对多，1客户下面有多个联系人
        customer.getLinkmanSet().add(linkman1);
        customer.getLinkmanSet().add(linkman2);

        //表达多对一； 某联系人属于哪个客户
        linkman1.setCustomer(customer);
        linkman2.setCustomer(customer);
        openSession.save(customer);
        //通过级联保存 下面可以不写
//        openSession.save(linkman1);
//        openSession.save(linkman2);
        transaction.commit();
        openSession.close();
    }


    @Test
    //1对多
    //在现有的基础上添加联系人
    public void test02(){
        //1.获得session
        Session openSession = HibernateUtils.getOpenSession();
        //2.获得事务
        Transaction transaction = openSession.beginTransaction();
        //3.操作
        //获得存在客户
        Customer customer = openSession.get(Customer.class, 7L);
        //创建联系人
        Linkman linkman=new Linkman();
        linkman.setName("new linkman cascade");
        linkman.setCustomer(customer);
        //将联系人添加到客户
        customer.getLinkmanSet().add(linkman);
        //new Linkman 需要通过save来保存，但是，可以通过级联操作来保存
        //openSession.save(linkman);
        transaction.commit();
        openSession.close();
    }

    /**
     * 删除客户 同时删除客户下面的联系人
     */
    @Test
    public void test03(){
        //1.获得session
        Session openSession = HibernateUtils.getOpenSession();
        //2.获得事务
        Transaction transaction = openSession.beginTransaction();
        //3.操作
        //1> 获得要操作的客户对象
        Customer customer = openSession.get(Customer.class, 7L);

        //需要在客户方设置级联 cascade delete或者all
        openSession.delete(customer);

        transaction.commit();
        openSession.close();
    }


}
