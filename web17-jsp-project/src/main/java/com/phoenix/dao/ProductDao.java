package com.phoenix.dao;

import com.phoenix.domain.Product;
import com.phoenix.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    public List<Product> getAllProducts() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product";
        List<Product> list = queryRunner.query(sql, new BeanListHandler<>(Product.class));
        return list;
    }

}
