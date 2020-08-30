package com.phoenix.dao;

public interface AccountDao {

    void addMoney(Integer id,Double money);

    void minusMoney(Integer id,Double money);


}
