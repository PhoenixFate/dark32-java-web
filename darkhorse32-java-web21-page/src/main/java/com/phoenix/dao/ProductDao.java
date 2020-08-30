package com.phoenix.dao;

import com.phoenix.domain.Product;
import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    public List<Product> findAllProduct() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
        return productList;
    }

    //获得全部的商品的条数
    public int getTotalCount() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from product";
        //Integer count = queryRunner.query(sql, new BeanHandler<Integer>(Integer.class));
        Long count = (Long) queryRunner.query(sql, new ScalarHandler());
        return count.intValue();
    }

    public List<Product> findProductListForPageBean(int index,int currentCount) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product limit ?,?";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class), index, currentCount);
        return productList;
    }
}
