package com.phoenix.shop.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        //加载配置文件
        InputStream inputStream = JedisPoolConfig.class.getClassLoader().getResourceAsStream("dev/redis.properties");
        Properties properties=new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //0.创建池子的配置对象
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxIdle")));
        //最大闲置连接数
        jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("redis.minIdle")));
        //最小闲置连接数
        jedisPoolConfig.setMinIdle(Integer.parseInt(properties.getProperty("redis.maxTotal")));
        jedisPool=new JedisPool(jedisPoolConfig,properties.getProperty("redis.url"),Integer.parseInt(properties.getProperty("redis.port")),100000,properties.getProperty("redis.password"),Integer.parseInt(properties.getProperty("redis.database")));
    }

    //通过jedis的pool获得jedis连接对象
    public static Jedis getJedis(){
        //2.从池子中获取redis的连接资源
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public static void main(String[] args) {
        Jedis jedis=getJedis();
        jedis.set("username","test");
        String username = jedis.get("username");
        System.out.println(username);
    }
}
