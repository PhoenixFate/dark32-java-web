package com.phoenix.transaction;

import com.phoenix.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class TransactionDemo2 {

    @Resource(name = "accountService")
    private AccountService accountService;

    /**
     * 注解配置aop事务
     */
    @Test
    public void test01(){
        accountService.transfer3(1,2,100d);
    }


}
