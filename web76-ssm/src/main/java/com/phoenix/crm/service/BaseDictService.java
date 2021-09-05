package com.phoenix.crm.service;

import com.phoenix.crm.pojo.BaseDict;

import java.util.List;

public interface BaseDictService {

    //查询
    public List<BaseDict> selectBaseDictListByCode(String code);
}
