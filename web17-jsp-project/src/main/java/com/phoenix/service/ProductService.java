package com.phoenix.service;

import com.phoenix.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() throws SQLException;

}
