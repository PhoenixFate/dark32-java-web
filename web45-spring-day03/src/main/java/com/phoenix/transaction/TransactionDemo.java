package com.phoenix.transaction;

import com.phoenix.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TransactionDemo {

    @Resource(name = "accountService")
    private AccountService accountService;

    /**
     * 编码式调用事务
     */
    @Test
    public void test01(){
        accountService.transfer(1,2,100d);
    }

    /**
     * xml配置aop事务
     */
    @Test
    public void test02(){
        accountService.transfer2(1,2,100d);
    }


}
