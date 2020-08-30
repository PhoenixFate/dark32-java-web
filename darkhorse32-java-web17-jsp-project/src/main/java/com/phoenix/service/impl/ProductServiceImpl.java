package com.phoenix.service.impl;

import com.phoenix.dao.ProductDao;
import com.phoenix.domain.Product;
import com.phoenix.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao=new ProductDao();


    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> allProducts = productDao.getAllProducts();
        return allProducts;
    }
}
