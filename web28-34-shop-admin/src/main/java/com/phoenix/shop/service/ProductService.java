package com.phoenix.shop.service;

import com.phoenix.shop.dao.ProductDao;
import com.phoenix.shop.domain.Product;
import com.phoenix.shop.vo.PageBean;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private ProductDao productDao=new ProductDao();


    public PageBean<Product> findByPage(Integer pageNumber, Integer pageSize) throws SQLException {
        Long totalCount = productDao.totalCount();
        PageBean<Product> pageBean=new PageBean<>();
        pageBean.setCurrentPage(pageNumber);
        pageBean.setTotalCount(totalCount.intValue());
        pageBean.setCurrentCount(pageSize);
        pageBean.setTotalPage((int)Math.ceil((totalCount.intValue()*1.0)/pageSize));
        List<Product> productList=productDao.findByPage(pageNumber,pageSize);
        pageBean.setList(productList);
        return pageBean;
    }


    public Integer addProduct(Product product) throws SQLException {
        Integer count = productDao.addProduct(product);
        return count;
    }

    public Integer deleteById(String pid) throws SQLException {
        Integer count = productDao.deleteById(pid);
        return count;
    }
}
