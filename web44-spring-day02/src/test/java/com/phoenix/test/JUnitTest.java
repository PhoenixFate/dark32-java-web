package com.phoenix.test;

import com.phoenix.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * spring 整合junit测试
 */
//自动创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪个配置文件
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class JUnitTest {

    //将名为user的对象注入到user变量中
    @Resource(name = "user")
    private User user;

    @Test
    public void fun1() {
        System.out.println(user);
    }
}
