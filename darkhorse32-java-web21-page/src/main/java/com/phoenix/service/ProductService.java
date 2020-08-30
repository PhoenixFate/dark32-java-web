package com.phoenix.service;

import com.phoenix.dao.ProductDao;
import com.phoenix.domain.Product;
import com.phoenix.vo.PageBean;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    public List<Product> findAllProduct() throws SQLException {
        ProductDao productDao=new ProductDao();
        List<Product> productList = productDao.findAllProduct();
        return productList;
    }


    //分页操作
    public PageBean<Product> findPageBean(int currentPage,int currentCount) throws SQLException {
        ProductDao productDao=new ProductDao();

        //目的：想办法封装一个PageBean，并返回
        PageBean<Product> pageBean=new PageBean<Product>();

        //设置相关属性：
        //1.当前页，客户端传过来的值
        pageBean.setCurrentPage(currentPage);
        //2.当前页显示的条数，一般为固定值，或客户端设置
        pageBean.setCurrentCount(currentCount);
        //3.总条数，数据库查询
        int totalCount=productDao.getTotalCount();
        pageBean.setTotalCount(totalCount);
        //4.总页数
        //
        //总页数=Math.ceil(总跳数/当前页显示的条数);
        //基本类型强转不报错
        int totalPage= (int) Math.ceil((1.0*totalCount)/currentCount);
        pageBean.setTotalPage(totalPage);
        //5.每页显示的数据List<T> productList;
        //索引index=（当前页-1）*每页显示的条数
        int index=(currentPage-1)*currentCount;
        List<Product> productList=productDao.findProductListForPageBean(index,currentCount);
        pageBean.setList(productList);

        return pageBean;
    }
}
