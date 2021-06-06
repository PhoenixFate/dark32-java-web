package com.phoenix.dao;

import com.phoenix.domain.Product;
import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao2 {

    public List<Product> findListforPageBean(Integer currentPage, Integer pageCount) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product limit ?,?";
        List<Product>  productList = queryRunner.query(sql, new BeanListHandler<>(Product.class), (currentPage-1) * pageCount, pageCount);
        return productList;
    }

    public Integer totalCount() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from product";
        Long count = (Long) queryRunner.query(sql, new ScalarHandler());
        System.out.println("count:-----------"+count);
        return count.intValue();

    }


}
