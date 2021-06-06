package com.phoenix.service;

import com.phoenix.dao.AdminProductDao;
import com.phoenix.domain.Category;
import com.phoenix.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class AdminProductService {

    public List<Product> findAllProduct() throws SQLException {
        AdminProductDao adminProductDao=new AdminProductDao();
        List<Product> productList=adminProductDao.findAllProduct();

        return productList;
    }

    //获得所有的类别
    public List<Category> findAllCategory() throws SQLException {
        AdminProductDao adminProductDao=new AdminProductDao();
        List<Category> categoryList= adminProductDao.findAllCategory();
        return categoryList;
    }

    public void addProduct(Product product) throws SQLException {
        AdminProductDao adminProductDao=new AdminProductDao();
        adminProductDao.addProduct(product);
    }

    public void deleteProductByPid(String pid) throws SQLException {
        AdminProductDao adminProductDao=new AdminProductDao();
        adminProductDao.deleteProductByPid(pid);

    }

    public Product showProductByPid(String pid) throws SQLException {
        AdminProductDao adminProductDao=new AdminProductDao();
        Product product = adminProductDao.showProductByPid(pid);
        return product;
    }

    public void updateProduct(Product product) throws SQLException {
        AdminProductDao adminProductDao=new AdminProductDao();
        adminProductDao.updateProduct(product);
    }
}
