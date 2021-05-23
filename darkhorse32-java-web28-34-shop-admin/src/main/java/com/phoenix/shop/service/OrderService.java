package com.phoenix.shop.service;

import com.phoenix.shop.dao.OrderDao;
import com.phoenix.shop.domain.Order;
import com.phoenix.shop.domain.OrderItem;
import com.phoenix.shop.domain.Product;
import com.phoenix.shop.vo.PageBean;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderService {

    private OrderDao orderDao=new OrderDao();

    public PageBean<Order> findByPage(Integer pageNumber, Integer pageSize) throws SQLException {
        Long totalCount = orderDao.totalCount();
        PageBean<Order> pageBean=new PageBean<>();
        pageBean.setCurrentPage(pageNumber);
        pageBean.setTotalCount(totalCount.intValue());
        pageBean.setCurrentCount(pageSize);
        pageBean.setTotalPage((int)Math.ceil((totalCount.intValue()*1.0)/pageSize));
        List<Order> orderList=orderDao.findByPage(pageNumber,pageSize);
        pageBean.setList(orderList);
        return pageBean;
    }

    public List<Map<String, Object>> findDetailByOid(String oid) throws SQLException {
        List<Map<String, Object>>  orderItemList = orderDao.findDetailByOid(oid);
        return orderItemList;
    }
}
