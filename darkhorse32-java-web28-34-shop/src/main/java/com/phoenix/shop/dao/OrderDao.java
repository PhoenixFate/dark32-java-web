package com.phoenix.shop.dao;

import com.phoenix.shop.domain.Order;
import com.phoenix.shop.domain.OrderItem;
import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    public Integer saveOrder(Order order) throws SQLException {
        QueryRunner queryRunner=new QueryRunner();
        String sql="insert into orders values(?,?,?,?,?,?,?,?)";
        Connection connection = DataSourceUtils.getConnection();

        Integer count =queryRunner.update(connection, sql, order.getOid(), order.getOrdertime(), order.getTotal(),
                order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
        return count;
    }

    public int[] saveOrderItems(Order order) throws SQLException {
        QueryRunner queryRunner=new QueryRunner();
        String sql="insert into orderItem values(?,?,?,?,?)";
        Connection connection = DataSourceUtils.getConnection();
        List<OrderItem> orderItems = order.getOrderItems();
        Object[][] totalParams=new Object[orderItems.size()][5];
        for(int i=0;i<orderItems.size();i++){
            totalParams[i][0]=orderItems.get(i).getItemid();
            totalParams[i][1]=orderItems.get(i).getCount();
            totalParams[i][2]=orderItems.get(i).getSubtotal();
            totalParams[i][3]=orderItems.get(i).getProduct().getPid();
            totalParams[i][4]=orderItems.get(i).getOrder().getOid();
        }
        int[] count =  queryRunner.batch(connection, sql, totalParams);
        return count;
    }

    public Integer updateOrder(Order order) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        // UPDATE Person SET Address = 'Zhongshan 23', City = 'Nanjing'
        String sql="update order set address=?,name=?,telephone=? where oid=?";
        int count = queryRunner.update(sql, order.getAddress(), order.getName(), order.getTelephone(), order.getOid());
        return count;
    }

    public Integer updateStatus(String r6_order) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource()) ;
        String sql="update order set status =1 where oid=?";
        int count = queryRunner.update(sql, r6_order);
        return count;
    }

    public List<Order> findByPage(String uid,Integer pageNumber,Integer pageSize) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from orders where uid=? limit ?,?";
        List<Order> orderList = queryRunner.query(sql, new BeanListHandler<>(Order.class), uid,(pageNumber-1)*pageSize,pageSize);
        return orderList;
    }

    public Integer countByUid(String uid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from orders where uid=?";
        Long count = (Long) queryRunner.query(sql, new ScalarHandler<>(), uid);
        return count.intValue();

    }
}
