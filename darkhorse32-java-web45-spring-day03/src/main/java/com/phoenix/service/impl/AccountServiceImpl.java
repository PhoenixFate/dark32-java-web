package com.phoenix.service.impl;

import com.phoenix.dao.AccountDao;
import com.phoenix.service.AccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,readOnly = false)
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    private TransactionTemplate transactionTemplate;

    /**
     * 代码手动配置事务
     * @param from
     * @param to
     * @param money
     */
    public void transfer(final Integer from,final Integer to,final Double money) {
        //编码式开启事务
        //观光代码，没什么鸟用
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                //减钱
                accountDao.minusMoney(from,money);
                //int i=1/0;
                //加钱
                accountDao.addMoney(to,money);
            }
        });
    }

    /**
     * xml配置aop事务
     * @param from
     * @param to
     * @param money
     */
    public void transfer2(final Integer from,final Integer to,final Double money) {
        //减钱
        accountDao.minusMoney(from,money);
        int i=1/0;
        //加钱
        accountDao.addMoney(to,money);
    }

    /**
     * 注解配置aop事务
     * @param from
     * @param to
     * @param money
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,readOnly = false)
    public void transfer3(final Integer from,final Integer to,final Double money) {
        //减钱
        accountDao.minusMoney(from,money);
        int i=1/0;
        //加钱
        accountDao.addMoney(to,money);
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
