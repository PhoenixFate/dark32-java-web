package com.phoenix.cart.service.impl;

import com.phoenix.cart.service.CartService;
import com.phoenix.common.jedis.JedisClient;
import com.phoenix.common.utils.JsonUtils;
import com.phoenix.item.mapper.ItemMapper;
import com.phoenix.item.pojo.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 购物车处理服务
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${APPLICATION_NAME}")
    private String APPLICATION_NAME;

    @Value("${CART_PREFIX}")
    private String CART_PREFIX;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void addCartToRedis(Long userId, Long itemId,Integer num) {
        //向redis中添加购物车
        //数据类型是hash；key：用户id，field：商品id

        //判断商品是否存在
        Boolean hexists = jedisClient.hexists(APPLICATION_NAME + ":" + CART_PREFIX + ":" + userId, itemId + "");
        if(hexists){
            //存在
            //如果存在，数量相加
            String itemJson = jedisClient.hget(APPLICATION_NAME + ":" + CART_PREFIX + ":" + userId, itemId + "");
            Item item = JsonUtils.jsonToPojo(itemJson, Item.class);
            if(item!=null){
                item.setNum(item.getNum()+num);
                jedisClient.hset(APPLICATION_NAME + ":" + CART_PREFIX + ":" + userId,itemId+"",JsonUtils.objectToJson(item));
            }
            return;
        }
        //如果不存在，根据商品id，获得商品详情并添加
        Item item = itemMapper.selectByPrimaryKey(itemId);
        item.setNum(num);
        if(StringUtils.isNotBlank(item.getImage())){
            item.setImage(item.getImage().split(",")[0]);
        }
        jedisClient.hset(APPLICATION_NAME + ":" + CART_PREFIX + ":" + userId,itemId+"",JsonUtils.objectToJson(item));
    }

    @Override
    public List<Item> getItemListByUserId(Long userId) {
        List<String> itemListJson = jedisClient.hvals(APPLICATION_NAME + ":" + CART_PREFIX + ":" + userId);
        return JsonUtils.jsonToList(itemListJson.toString(), Item.class);
    }

    @Override
    public void saveItemList(Long userId, List<Item> itemList) {
        jedisClient.del(APPLICATION_NAME + ":" + CART_PREFIX + ":" + userId);
        for(Item item:itemList){
            jedisClient.hset(APPLICATION_NAME + ":" + CART_PREFIX + ":" + userId,item.getId()+"",JsonUtils.objectToJson(item));
        }
    }

    @Override
    public void mergeCart(Long userId, List<Item> itemList) {
        for(Item item:itemList){
            this.addCartToRedis(userId,item.getId(),item.getNum());
        }

    }

    @Override
    public void clearCart(Long userId) {
        jedisClient.del(APPLICATION_NAME + ":" + CART_PREFIX + ":" + userId);
    }

}
