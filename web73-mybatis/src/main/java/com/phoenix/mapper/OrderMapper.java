package com.phoenix.mapper;

import com.phoenix.pojo.Orders;

import java.util.List;

public interface OrderMapper {

    List<Orders> getAllOrderList();

    List<Orders> selectOrdersWithUser();
}
