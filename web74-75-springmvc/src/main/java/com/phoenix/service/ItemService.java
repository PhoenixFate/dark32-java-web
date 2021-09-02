package com.phoenix.service;

import com.phoenix.pojo.Items;

import java.util.List;

public interface ItemService {

    List<Items> getAllList();

    Items getInfo(Integer id);

    Boolean updateItem(Items items);
}
