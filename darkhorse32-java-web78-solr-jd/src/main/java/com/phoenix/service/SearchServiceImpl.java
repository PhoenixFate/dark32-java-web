package com.phoenix.service;

import com.phoenix.dao.SearchDao;
import com.phoenix.pojo.ProductModel;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    public List<ProductModel> searchProductList(String queryString, String catalog_name, String price, String sort) throws IOException, SolrServerException {
        List<ProductModel> productModelList = searchDao.searchProductList(queryString, catalog_name, price, sort);
        return productModelList;
    }
}
