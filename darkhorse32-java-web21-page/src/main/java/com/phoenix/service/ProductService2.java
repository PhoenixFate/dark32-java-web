package com.phoenix.service;

import com.phoenix.dao.ProductDao2;
import com.phoenix.domain.Product;
import com.phoenix.vo.PageBean;

import java.sql.SQLException;
import java.util.List;

public class ProductService2 {

    public PageBean<Product> findPageBean(Integer currentPage, Integer currentCount) throws SQLException {
        ProductDao2 productDao2=new ProductDao2();
        List<Product> productList = productDao2.findListforPageBean(currentPage, currentCount);

        Integer count = productDao2.totalCount();

        PageBean<Product> pageBean=new PageBean<>();
        pageBean.setCurrentCount(currentCount);
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(productList);
        pageBean.setTotalCount(count);
        pageBean.setTotalPage(count%currentCount==0?count/currentCount:count/currentCount+1);



        return pageBean;
    }

}
