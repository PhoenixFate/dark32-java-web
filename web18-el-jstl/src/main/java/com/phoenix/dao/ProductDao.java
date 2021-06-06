package com.phoenix.dao;

import com.phoenix.domain.Product;
import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    public List<Product> findAllProduct() throws SQLException {
        QueryRunner  queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
        return productList;
    }

    public Product getInfo(String id) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product where pid=?";
        Product product = queryRunner.query(sql, new BeanHandler<>(Product.class), id);
        System.out.println(product);
        return product;

    }
}
