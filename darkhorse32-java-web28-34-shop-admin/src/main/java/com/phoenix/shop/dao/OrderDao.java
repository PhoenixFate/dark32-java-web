package com.phoenix.shop.dao;

import com.phoenix.shop.domain.Order;
import com.phoenix.shop.domain.OrderItem;
import com.phoenix.shop.domain.Product;
import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDao {

    public List<Order> findByPage(Integer pageNumber , Integer pageSize) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from orders limit ?,?";
        List<Order> orderList=queryRunner.query(sql,new BeanListHandler<>(Order.class),(pageNumber-1)*pageSize,pageSize);
        return orderList;
    }

    public Long totalCount() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from orders ";
        Long count=queryRunner.query(sql,new ScalarHandler<>());
        return count;
    }

    public  List<Map<String, Object>>  findDetailByOid(String oid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select p.pimage,p.pname,p.shop_price,oi.count,oi.subtotal from orderitem oi,product p where oid=? and oi.pid=p.pid";
        List<Map<String, Object>> orderItemList = queryRunner.query(sql, new MapListHandler(), oid);
        return orderItemList;
    }
}
