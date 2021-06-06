package com.phoenix.shop.dao;

import com.phoenix.shop.domain.Product;
import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    public Product findById(String id) throws SQLException {
        QueryRunner queryRunner=new QueryRunner((DataSourceUtils.getDataSource()));
        String sql="select * from product where pflag=0 and pid=?";
        Product product=queryRunner.query(sql,new BeanHandler<>(Product.class),id);
        return product;
    }

    public List<Product> findByPage(Integer pageNumber , Integer pageSize) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product where pflag=0 limit ?,?";
        List<Product> productList=queryRunner.query(sql,new BeanListHandler<>(Product.class),(pageNumber-1)*pageSize,pageSize);
        return productList;
    }

    public Long totalCount() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from product where pflag=0";
        Long count=queryRunner.query(sql,new ScalarHandler<>());
        return count;
    }


    public Integer addProduct(Product product) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
        int count = queryRunner.update(sql, product.getPid(), product.getPname(),
                product.getMarket_price(), product.getShop_price(), product.getPimage(), product.getPdate(),
                product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid());
        return count;
    }

    public Integer deleteById(String pid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="delete from product where pid=?";
        int count = queryRunner.update(sql, pid);
        return count;
    }
}
