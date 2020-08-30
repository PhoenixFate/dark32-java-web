package com.phoenix.dao;

import com.phoenix.domain.Product;
import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.List;

public class ReviewProductDao {

    public List<Product> getAllList() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product ";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<>(Product.class));
        return productList;
    }


}