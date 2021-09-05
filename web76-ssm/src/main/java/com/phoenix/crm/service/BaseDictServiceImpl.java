package com.phoenix.crm.service;

import java.util.List;

import com.phoenix.crm.dao.BaseDictDao;
import com.phoenix.crm.pojo.BaseDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BaseDictServiceImpl implements BaseDictService {


    @Autowired
    private BaseDictDao baseDictDao;

    public List<BaseDict> selectBaseDictListByCode(String code) {
        return baseDictDao.selectBaseDictListByCode(code);
    }


}
