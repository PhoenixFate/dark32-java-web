package com.phoenix.shop.service;

import com.phoenix.shop.dao.CategoryDao;
import com.phoenix.shop.domain.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao=new CategoryDao();

    public List<Category> getAll() throws SQLException {
        List<Category> categoryList = categoryDao.getAll();
        return categoryList;
    }


}
