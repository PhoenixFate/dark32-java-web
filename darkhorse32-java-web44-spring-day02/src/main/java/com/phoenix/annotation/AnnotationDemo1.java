package com.phoenix.annotation;

import com.phoenix.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemo1 {

    /**
     * @Component
     */
    @Test
    public void test01(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");

        Object user = applicationContext.getBean("user");

        System.out.println(user);

    }

    /**
     * @Scope
     */
    @Test
    public void test02(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");

        Object user1 = applicationContext.getBean("user");
        Object user2 = applicationContext.getBean("user");

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1==user2);

    }


    /**
     * @Value
     */
    @Test
    public void test03(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");

        Object user = applicationContext.getBean("user");

        System.out.println(user);

        Object user2 = applicationContext.getBean("user");
        System.out.println(user2);
    }

    /**
     * @Autowired
     * @Qulifier
     */
    @Test
    public void test04(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");

        User user1 = (User) applicationContext.getBean("user");

        System.out.println(user1);
        System.out.println(System.identityHashCode(user1.getCar()));
        System.out.println(user1.getCar());
        user1.getCar().setName("user1 car");


        User user2 = (User) applicationContext.getBean("user");
        System.out.println(user2);
        System.out.println(System.identityHashCode(user2.getCar()));
        System.out.println(user2.getCar());
    }

    /**
     * @Resource(name="xxx")
     */
    @Test
    public void test05(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");

        User user1 = (User) applicationContext.getBean("user");

        System.out.println(user1);
        System.out.println(System.identityHashCode(user1.getCar()));
        System.out.println(user1.getCar());

        User user2 = (User) applicationContext.getBean("user");
        System.out.println(user2);
        System.out.println(System.identityHashCode(user2.getCar()));
        System.out.println(user2.getCar());
    }


    /**
     * @PostConstructor
     * @PreDestroy
     */
    @Test
    public void test06(){
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");

        User user1 = (User) applicationContext.getBean("user");

        System.out.println(user1);
        System.out.println(System.identityHashCode(user1.getCar()));
        System.out.println(user1.getCar());

        User user2 = (User) applicationContext.getBean("user");
        System.out.println(user2);
        System.out.println(System.identityHashCode(user2.getCar()));
        System.out.println(user2.getCar());

        applicationContext.close();
    }
}
