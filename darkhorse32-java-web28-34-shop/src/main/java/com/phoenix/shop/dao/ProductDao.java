package com.phoenix.shop.dao;

import com.phoenix.shop.domain.Product;
import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDao {

    public  List<Product> hotProducts() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product where is_hot=1 and pflag=0 order by pdate desc limit ?";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<>(Product.class),9);
        return productList;
    }

    public List<Product> newProducts() throws SQLException {
        QueryRunner queryRunner=new QueryRunner((DataSourceUtils.getDataSource()));
        String sql="select * from product where pflag=0 order by pdate desc limit ?";
        List<Product> productList=queryRunner.query(sql,new BeanListHandler<>(Product.class),9);
        return productList;
    }

    public Product findById(String id) throws SQLException {
        QueryRunner queryRunner=new QueryRunner((DataSourceUtils.getDataSource()));
        String sql="select * from product where pflag=0 and pid=?";
        Product product=queryRunner.query(sql,new BeanHandler<>(Product.class),id);
        return product;
    }

    public List<Product> findByCid(String cid,Integer pageNumber , Integer pageSize) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product where pflag=0 and cid=? limit ?,?";
        List<Product> productList=queryRunner.query(sql,new BeanListHandler<>(Product.class),cid,(pageNumber-1)*pageSize,pageSize);
        return productList;
    }

    public Long totalCountByCid(String cid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select count(*) from product where pflag=0 and cid=?";
        Long count=queryRunner.query(sql,new ScalarHandler<>(),cid);
        return count;
    }

    public List<Product>  findByIds(String[] ids) throws SQLException {
        QueryRunner queryRunner=new QueryRunner((DataSourceUtils.getDataSource()));
        String sql="select * from product where pflag=0 and pid in (";
        String orderBy="order by field(pid,";
        List<String> list=new ArrayList<>(Arrays.asList(ids));
        for(int i=0;i<ids.length;i++){
            sql+="?,";
            orderBy+="?,";
            list.add(ids[i]);
        }
        sql=sql.substring(0,sql.length()-1)+")";
        orderBy=orderBy.substring(0,orderBy.length()-1)+")";
        System.out.println(sql+orderBy);
        List<Product> productList = queryRunner.query(sql+orderBy, new BeanListHandler<>(Product.class), list.toArray());
        return  productList;
    }
}
