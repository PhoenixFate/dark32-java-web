package com.phoenix.dao;

import com.phoenix.domain.Category;
import com.phoenix.domain.Product;
import com.phoenix.utils.DataSourceUtils;
import com.phoenix.vo.Condition;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminProductDao {

    public List<Product> findAllProduct() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product ";
        List<Product> productList = queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
        return productList;
    }

    public List<Category> findAllCategory() throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from category";
        List<Category> categoryList=queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
        return categoryList;
    }

    public int addProduct(Product product) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
        int count = queryRunner.update(sql, product.getPid(), product.getPname(), product.getMarket_price(),
                product.getShop_price(), product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid());
        return count;
    }


    public void deleteProductByPid(String pid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="delete from product where pid=?";
        queryRunner.update(sql,pid);
    }

    public Product showProductByPid(String pid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from product where pid=?";
        Product product = queryRunner.query(sql, new BeanHandler<Product>(Product.class),pid);
        return product;
    }

    public void updateProduct(Product product) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
        int count = queryRunner.update(sql, product.getPname(), product.getMarket_price(), product.getShop_price(), product.getPimage(), product.getPdate(), product.getIs_hot()
                , product.getPdesc(), product.getPflag(), product.getCid(), product.getPid());
        System.out.println("count: "+count);
    }

    public List<Product> findProductListByCondition(Condition condition) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        StringBuffer sql=new StringBuffer("select * from product where 1=1 ");
        //定义一个容器，存储实际参数
        List<String> params=new ArrayList<String >();
        if(condition.getPname()!=null && !("".equals(condition.getPname().trim()) )){
            sql.append(" and pname like ? ");
            params.add("%"+condition.getPname().trim()+"%");
        }
        if(condition.getIsHot()!=null && !("".equals(condition.getIsHot().trim()))){
            sql.append(" and is_hot=? ");
            params.add(condition.getIsHot().trim());
        }
        if(condition.getCid()!=null && !("".equals(condition.getCid().trim()))){
            sql.append(" and cid=? ");
            params.add(condition.getCid().trim());
        }
        Object[] objects = params.toArray();
        List<Product> productList = queryRunner.query(sql.toString(), new BeanListHandler<Product>(Product.class), objects);
        return productList;
    }
}
