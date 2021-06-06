package com.phoenix.service;

import com.phoenix.dao.ReviewProductDao;
import com.phoenix.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ReviewProductService {

    public List<Product> getAllProductList() throws SQLException {
        ReviewProductDao reviewProductDao=new ReviewProductDao();
        List<Product> productList = reviewProductDao.getAllList();
        return productList;
    }


}
