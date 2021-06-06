package com.phoenix.dao.impl;

import com.phoenix.dao.AccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    public void addMoney(Integer id, Double money) {
        String sql="update tb_account set money=money+? where id=?";
        getJdbcTemplate().update(sql,money,id);
    }

    public void minusMoney(Integer id, Double money) {
        String sql="update tb_account set money=money-? where id=?";
        getJdbcTemplate().update(sql,money,id);
    }

}
