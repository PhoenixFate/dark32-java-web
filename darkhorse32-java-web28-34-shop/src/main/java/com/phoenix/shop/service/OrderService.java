package com.phoenix.shop.service;

import com.phoenix.shop.dao.OrderDao;
import com.phoenix.shop.dao.OrderItemDao;
import com.phoenix.shop.domain.Order;
import com.phoenix.shop.domain.OrderItem;
import com.phoenix.shop.domain.Product;
import com.phoenix.shop.utils.DataSourceUtils;
import com.phoenix.shop.vo.PageBean;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {

    private OrderDao orderDao=new OrderDao();
    private OrderItemDao orderItemDao=new OrderItemDao();

    public void submit(Order order) {
        OrderDao orderDao=new OrderDao();
        try {
            //1. 开启事务
            DataSourceUtils.startTransaction();
            //2. 调用dao存储order
            Integer orderCount = orderDao.saveOrder(order);
            System.out.println("orderCount: "+orderCount);
            //3. 调用dao存储orderItems
            int[] orderItemsCount = orderDao.saveOrderItems(order);
            System.out.println("orderItemsCount: "+orderItemsCount);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DataSourceUtils.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            //提交事务
            try {
                DataSourceUtils.commitAndRelease();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer updateOrder(Order order) throws SQLException {
        Integer count = orderDao.updateOrder(order);
        return count;
    }

    public Integer updateStatus(String r6_order) throws SQLException {
        Integer count = orderDao.updateStatus(r6_order);
        return count;
    }

    public  PageBean<Order> findByPage(String uid,Integer pageNumber,Integer pageSize) throws SQLException, InvocationTargetException, IllegalAccessException {
        List<Order> orderList = orderDao.findByPage(uid,pageNumber,pageSize);
        for(Order order:orderList){
            List<Map<String, Object>> mapList = orderItemDao.findByOid(order.getOid());
            List<OrderItem> orderItemList=new ArrayList<>();
            for(Map map:mapList){
                OrderItem orderItem=new OrderItem();
                BeanUtils.populate(orderItem,map);
                Product product=new Product();
                BeanUtils.populate(product,map);
                orderItem.setProduct(product);
                orderItemList.add(orderItem);
            }
            order.setOrderItems(orderItemList);
        }
        Integer count = orderDao.countByUid(uid);
        PageBean<Order> pageBean=new PageBean<>();
        pageBean.setList(orderList);
        pageBean.setCurrentPage(pageNumber);
        pageBean.setTotalCount(count);
        pageBean.setTotalPage( (int)Math.ceil(count*1.0/pageSize)  );
        pageBean.setCurrentCount(pageSize);
        return pageBean;
    }
}
