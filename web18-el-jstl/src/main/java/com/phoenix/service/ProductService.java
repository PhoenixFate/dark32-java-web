package com.phoenix.service;

import com.phoenix.dao.ProductDao;
import com.phoenix.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private ProductDao productDao=new ProductDao();


    public List<Product> findAllProduct() throws SQLException {
        //业务层暂时没有复杂业务
        //传递请求到dao层
        List<Product> productList=productDao.findAllProduct();

        return productList;
    }

    public Product getInfo(String id) throws SQLException {
        Product product = productDao.getInfo(id);
        return product;
    }
}
