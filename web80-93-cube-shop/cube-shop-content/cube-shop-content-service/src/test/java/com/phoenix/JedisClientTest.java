package com.phoenix;

import com.phoenix.common.jedis.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JedisClientTest {

    /**
     * 单机版redis
     */
    @Test
    public void test01(){
        //初始化spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient=applicationContext.getBean(JedisClient.class);
        System.out.println(jedisClient);
        jedisClient.set("testClient","testClient");
        System.out.println(jedisClient.get("testClient"));
    }

    /**
     * 集群版redis
     */
    @Test
    public void test2(){
        //初始化spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient=applicationContext.getBean(JedisClient.class);
        System.out.println(jedisClient);
        jedisClient.set("testClient","testClient");
        System.out.println(jedisClient.get("testClient"));
    }


}
