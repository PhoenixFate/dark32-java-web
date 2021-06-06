package com.phoenix.shop.service;

import com.phoenix.shop.dao.CategoryDao;
import com.phoenix.shop.domain.Category;
import com.phoenix.shop.vo.PageBean;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao=new CategoryDao();

    public PageBean<Category> findByPage(Integer pageNumber, Integer pageSize) throws SQLException {
        Long totalCount = categoryDao.totalCount();
        PageBean<Category> pageBean=new PageBean<>();
        pageBean.setCurrentPage(pageNumber);
        pageBean.setTotalCount(totalCount.intValue());
        pageBean.setCurrentCount(pageSize);
        pageBean.setTotalPage((int)Math.ceil((totalCount.intValue()*1.0)/pageSize));
        List<Category> categoryList=categoryDao.findByPage(pageNumber,pageSize);
        pageBean.setList(categoryList);
        return pageBean;
    }


    public Integer addCategory(Category category) throws SQLException {
        Integer count = categoryDao.addCategory(category);
        return count;
    }

    public Category findById(String id) throws SQLException {
        Category category = categoryDao.findById(id);
        return category;
    }

    public Integer updateCategory(Category category) throws SQLException {
        Integer count = categoryDao.updateCategory(category);
        return count;
    }

    public List<Category> findAll() throws SQLException {
        List<Category> categoryList = categoryDao.findAll();
        return categoryList;
    }

    public Integer deleteById(String cid) throws SQLException {
        Integer count = categoryDao.deleteById(cid);
        return count;
    }
}
