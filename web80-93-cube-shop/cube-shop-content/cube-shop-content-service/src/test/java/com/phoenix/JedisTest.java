package com.phoenix;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    /**
     * jedis
     */
    @Test
    public void test01(){
        //每一个jedis对象代表一个连接
        // 1. 创建连接redis的jedis对象，参数host port
        Jedis jedis=new Jedis("www.bytes-space.com",6379);
        jedis.auth("123456");
        // 2. 直接使用jedis操作redis
        jedis.set("test01","sss");
        System.out.println(jedis.get("test01"));
        // 3. 关闭连接
        jedis.close();
    }

    /**
     * JedisPool
     */
    @Test
    public void test02(){
        //JedisPool
        GenericObjectPoolConfig genericObjectPoolConfig=new GenericObjectPoolConfig();
        //创建连接池对象
        JedisPool jedisPool=new JedisPool(genericObjectPoolConfig,"www.bytes-space.com",6379,10000,"123456",1);
        //从连接池获得jedis对象
        Jedis jedis = jedisPool.getResource();
        //使用jedis操作
        jedis.set("test02","kkk");
        System.out.println(jedis.get("test02"));
        //关闭连接
        jedis.close();
        //关闭连接池
        jedisPool.close();
    }

    /**
     * JedisCluster
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        //set，set中包含若干个 HostAndPort对象
        Set<HostAndPort> clusterNames=new HashSet<>();
        clusterNames.add(new HostAndPort("www.bytes-space.com",6390));
        clusterNames.add(new HostAndPort("www.bytes-space.com",6391));
        clusterNames.add(new HostAndPort("www.bytes-space.com",6392));
        clusterNames.add(new HostAndPort("www.bytes-space.com",6393));
        clusterNames.add(new HostAndPort("www.bytes-space.com",6394));
        clusterNames.add(new HostAndPort("www.bytes-space.com",6395));
        GenericObjectPoolConfig genericObjectPoolConfig=new GenericObjectPoolConfig();
        //创建连接redis集群对象
        JedisCluster jedisCluster=new JedisCluster(clusterNames,10000,10000,10000,"centos123qwer",genericObjectPoolConfig);
        // 使用JedisCluster操作redis JedisCluster自带连接池，不用关闭
        jedisCluster.set("test03","test03");
        System.out.println(jedisCluster.get("test03"));
        //关闭jedisCluster对象
        jedisCluster.close();
    }

}
