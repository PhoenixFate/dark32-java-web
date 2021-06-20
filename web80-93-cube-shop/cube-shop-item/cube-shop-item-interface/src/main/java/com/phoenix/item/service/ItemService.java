package com.phoenix.item.service;


import com.phoenix.common.pojo.EasyUIDataGridResult;
import com.phoenix.item.pojo.Item;
import com.phoenix.common.utils.CommonResult;
import com.phoenix.item.pojo.ItemDescription;

public interface ItemService {

    Item getItemById(Long id);
    EasyUIDataGridResult getItemList(Integer page,Integer rows);
    CommonResult addItem(Item item,String desc);
    ItemDescription getItemDescriptionById(Long id);

}
