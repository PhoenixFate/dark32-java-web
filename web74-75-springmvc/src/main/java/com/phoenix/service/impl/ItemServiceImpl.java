package com.phoenix.service.impl;

import com.phoenix.dao.ItemsMapper;
import com.phoenix.pojo.Items;
import com.phoenix.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> getAllList() {
        List<Items> items = itemsMapper.selectByExample(null);
        return items;
    }

    @Override
    public Items getInfo(Integer id) {
        Items items = itemsMapper.selectByPrimaryKey(id);
        return items;
    }

    @Override
    public Boolean updateItem(Items items) {
        int i = itemsMapper.updateByPrimaryKeySelective(items);
        return i > 0;
    }
}
