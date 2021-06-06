package com.phoenix.annotation_aop;

import com.phoenix.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//自动创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器时使用哪个配置文件
@ContextConfiguration(value = "classpath:com/phoenix/annotation_aop/applicationContext.xml")
public class Demo {

    @Resource(name = "userServiceTarget")
    private UserService userService;


    /**
     * aop测试
     */
    @Test
    public void fun1() {
        userService.save();
    }


}
