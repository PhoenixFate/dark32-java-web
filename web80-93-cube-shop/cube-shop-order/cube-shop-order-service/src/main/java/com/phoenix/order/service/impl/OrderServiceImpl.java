package com.phoenix.order.service.impl;

import com.phoenix.common.jedis.JedisClient;
import com.phoenix.order.mapper.OrderItemMapper;
import com.phoenix.order.mapper.OrderMapper;
import com.phoenix.order.mapper.OrderShippingMapper;
import com.phoenix.order.pojo.OrderInfo;
import com.phoenix.order.pojo.OrderItem;
import com.phoenix.order.pojo.OrderShipping;
import com.phoenix.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_ID_GEN_KEY}")
    private String ORDER_ID_GEN_KEY;

    @Value("${ORDER_ID_START}")
    private String ORDER_ID_START;

    @Value("${ORDER_DETAIL_ID_GEN_KEY}")
    private String ORDER_DETAIL_ID_GEN_KEY;

    @Value("${ORDER_DETAIL_ID_START}")
    private String ORDER_DETAIL_ID_START;


    @Override
    public String createOrder(OrderInfo orderInfo) {
        //生成订单号，使用redis的incr生成
        //不存在，设置初始值
        if(!jedisClient.exists(ORDER_ID_GEN_KEY)){
            jedisClient.set(ORDER_ID_GEN_KEY,ORDER_ID_START);
        }
        String orderId = jedisClient.incr(ORDER_ID_GEN_KEY)+"";
        //补全Order的数据
        orderInfo.setOrderId(orderId);
        orderInfo.setStatus(1);//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        //插入订单表
        orderMapper.insert(orderInfo);
        //向订单明细表插入数据
        List<OrderItem> orderItemList=orderInfo.getOrderItems();
        if(!jedisClient.exists(ORDER_DETAIL_ID_GEN_KEY)){
            jedisClient.set(ORDER_DETAIL_ID_GEN_KEY,ORDER_DETAIL_ID_START);
        }
        for(OrderItem orderItem:orderItemList){
            String orderItemId=jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY)+"";
            orderItem.setId(orderItemId);
            orderItem.setOrderId(orderId);
            orderItemMapper.insert(orderItem);
        }
        //向订单物流插入数据
        OrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);
        //返回订单号
        return orderId;
    }
}
