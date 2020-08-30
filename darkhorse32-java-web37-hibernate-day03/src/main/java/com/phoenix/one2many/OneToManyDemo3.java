package com.phoenix.one2many;

import com.phoenix.domain.Customer;
import com.phoenix.domain.Linkman;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 一对多，多对一操作
 * 关系维护
 * inverse true 1的一方放弃关系维护
 */
public class OneToManyDemo3 {

    @Test
    //1对多 添加
    public void test01(){
        //1.获得session
        Session openSession = HibernateUtils.getOpenSession();

        //2.获得事务
        Transaction transaction = openSession.beginTransaction();

        //3.操作
        Customer customer=new Customer();
        customer.setCustomerName("oneToMany inverse");

        Linkman linkman1=new Linkman();
        linkman1.setName("o2m linkman1 inverse");
        Linkman linkman2=new Linkman();
        linkman2.setName("o2m linkman2 inverse");

        //表达1对多，1客户下面有多个联系人
        //如果客户放弃维护于联系人的关系（应该放弃 inverse=true），维护关系的代码可以省略
        //customer.getLinkmanSet().add(linkman1);
        //customer.getLinkmanSet().add(linkman2);

        //表达多对一； 某联系人属于哪个客户
        linkman1.setCustomer(customer);
        linkman2.setCustomer(customer);

        //保存时两房都会维护1对多的关系，冗余了
        //解决方案： inverse="true"
        openSession.save(customer);
        openSession.save(linkman1);
        openSession.save(linkman2);
        transaction.commit();
        openSession.close();
    }



    @Test
    //1对多 删除客户
    public void test02(){
        //1.获得session
        Session openSession = HibernateUtils.getOpenSession();

        //2.获得事务
        Transaction transaction = openSession.beginTransaction();

        //3.操作
        Customer customer=openSession.get(Customer.class,10L);

        openSession.delete(customer);

        transaction.commit();
        openSession.close();
    }


}
