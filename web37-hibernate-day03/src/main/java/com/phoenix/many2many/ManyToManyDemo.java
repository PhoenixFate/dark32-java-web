package com.phoenix.many2many;

import com.phoenix.domain.Role;
import com.phoenix.domain.User;
import com.phoenix.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 多对多的操作
 */
public class ManyToManyDemo {

    /**
     * 保存员工以及 员工下面的角色
     */
    @Test
    public void test01(){
        //1.获得session
        Session openSession = HibernateUtils.getOpenSession();
        //2.获得事务
        Transaction transaction = openSession.beginTransaction();
        //3.操作
        //创建一个User，两个Role
        User user=new User();
        user.setUserName("many to many2");
        user.setUserCode("m0099");
        user.setUserPassword("123");
        user.setUserState('1');
        Role role1=new Role();
        role1.setRoleName("清洁工");
        Role role2=new Role();
        role2.setRoleName("管理员");

        //用户表达关系
        user.getRoles().add(role1);
        user.getRoles().add(role2);
        //角色表达关系
        role1.getUsers().add(user);
        role2.getUsers().add(user);

        openSession.save(user);
        openSession.save(role1);
        openSession.save(role2);
        transaction.commit();
        openSession.close();
    }

    /**
     * 为现有的员工添加一个角色
     */
    @Test
    public void test02(){
        //1.获得session
        Session openSession = HibernateUtils.getOpenSession();
        //2.获得事务
        Transaction transaction = openSession.beginTransaction();
        //3.操作
        //获得当前员工
        User user = openSession.get(User.class, 9L);
        Role role = openSession.get(Role.class, 4L);
        user.getRoles().add(role);

        transaction.commit();
        openSession.close();
    }


    /**
     * 为现有的员工解除一个角色
     */
    @Test
    public void test03(){
        //1.获得session
        Session openSession = HibernateUtils.getOpenSession();
        //2.获得事务
        Transaction transaction = openSession.beginTransaction();
        //3.操作
        //获得当前员工
        User user = openSession.get(User.class, 9L);
        //获得要操作的角色对象
        Role role = openSession.get(Role.class, 4L);
        //将角色从用户的角色集合中移除
        user.getRoles().remove(role);


        transaction.commit();
        openSession.close();
    }


}
