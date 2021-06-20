package com.phoenix.cart.service;

import com.phoenix.item.pojo.Item;
import java.util.List;

public interface CartService {

    void addCartToRedis(Long userId,Long itemId,Integer num);

    List<Item> getItemListByUserId(Long userId);

    void saveItemList(Long userId,List<Item> itemList);

    void mergeCart(Long userId,List<Item> itemList);

    void clearCart(Long userId);
}
