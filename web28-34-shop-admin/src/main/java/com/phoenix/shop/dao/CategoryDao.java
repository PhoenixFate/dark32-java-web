package com.phoenix.shop.dao;

import com.phoenix.shop.domain.Category;
import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    public Category findById(String id) throws SQLException {
        QueryRunner queryRunner=new QueryRunner((DataSourceUtils.getDataSource()));
        String sql="select * from category where cid=?";
        Category category=queryRunner.query(sql,new BeanHandler<>(Category.class),id);
        return category;
    }

    public List<Category> findByPage(Integer pageNumber , Integer pageSize) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from category limit ?,?";
        List<Category> categoryList=queryRunner.query(sql,new BeanListHandler<>(Category.class),(pageNumber-1)*pageSize,pageSize);
        return categoryList;
    }

    public Long totalCount() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from category";
        Long count=queryRunner.query(sql,new ScalarHandler<>());
        return count;
    }


    public Integer addCategory(Category category) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="insert into category values(?,?)";
        int count = queryRunner.update(sql, category.getCid(), category.getCname());
        return count;
    }

    public Integer updateCategory(Category category) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="update category set cname=? where cid=?";
        int count = queryRunner.update(sql, category.getCname(), category.getCid());
        return count;
    }

    public List<Category>  findAll() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from category";
        List<Category> categoryList=queryRunner.query(sql,new BeanListHandler<>(Category.class));
        return categoryList;
    }

    public Integer deleteById(String cid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="delete from category where cid=?";
        int count = queryRunner.update(sql, cid);
        return count;
    }
}
