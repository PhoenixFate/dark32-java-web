package com.phoenix.crm.dao;

import com.phoenix.crm.pojo.BaseDict;

import java.util.List;

public interface BaseDictDao {

    //查询
    public List<BaseDict> selectBaseDictListByCode(String code);
}
