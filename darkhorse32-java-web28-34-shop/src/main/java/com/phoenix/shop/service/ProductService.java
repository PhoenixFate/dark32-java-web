package com.phoenix.shop.service;

import com.phoenix.shop.dao.ProductDao;
import com.phoenix.shop.domain.Product;
import com.phoenix.shop.vo.PageBean;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private ProductDao productDao=new ProductDao();

    public List<Product> hotProducts() throws SQLException {
        List<Product> productList = productDao.hotProducts();
        return productList;
    }

    public List<Product> newProducts() throws SQLException {
        List<Product> productList=productDao.newProducts();
        return productList;
    }

    public Product findById(String id) throws SQLException {
        Product product=productDao.findById(id);
        return product;
    }

    public List<Product> findByIds(String[] ids) throws SQLException {
        List<Product> productList = productDao.findByIds(ids);
        return productList;
    }

    public PageBean<Product> findByCid(String cid,Integer pageNumber,Integer pageSize) throws SQLException {
        Long totalCount = productDao.totalCountByCid(cid);
        PageBean<Product> pageBean=new PageBean<>();
        pageBean.setCurrentPage(pageNumber);
        pageBean.setTotalCount(totalCount.intValue());
        pageBean.setCurrentCount(pageSize);
        pageBean.setTotalPage((int)Math.ceil((totalCount.intValue()*1.0)/pageSize));
        List<Product> productList=productDao.findByCid(cid,pageNumber,pageSize);
        pageBean.setList(productList);
        return pageBean;
    }

}
