package com.phoenix;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

    //通过java程序 访问redis数据库
    //通过jedis操作redis
    @Test
    public void test1(){
        //1. 获得链接对象
        Jedis jedis=new Jedis("114.67.89.253",40379);
        jedis.auth("centos123qwer");
        //2. 获得数据
        String username=jedis.get("username");
        System.out.println(username);
        //3.存储
        jedis.set("address","北京");
        System.out.println(jedis.get("address"));


    }

    //通过jedis的pool获得jedis连接对象
    @Test
    public void test2(){
        //0.创建池子的配置对象
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(30); //最大闲置个数
        jedisPoolConfig.setMinIdle(10); //最小闲置个数
        jedisPoolConfig.setMaxTotal(50);//最大连接数
        //1.创建一个redis连接池
        JedisPool jedisPool=new JedisPool(jedisPoolConfig,"114.67.89.253",40379,60000,"centos123qwer");

        //2.从池子中获取redis的连接资源
        Jedis jedis = jedisPool.getResource();
        //3.操作数据库
        jedis.set("abc","abc");
        System.out.println(jedis.get("abc"));

        //4.关闭资源
        jedis.close();
        jedisPool.close();


    }




}
