package com.phoenix.shop.dao;

import com.phoenix.shop.domain.Category;
import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    public List<Category> getAll() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from category ";
        List<Category> categoryList = queryRunner.query(sql, new BeanListHandler<>(Category.class));
        return categoryList;
    }

}
