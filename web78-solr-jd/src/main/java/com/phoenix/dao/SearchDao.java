package com.phoenix.dao;

import com.phoenix.pojo.ProductModel;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SearchDao {

    List<ProductModel> searchProductList(String queryString, String catalog_name, String price, String sort) throws IOException, SolrServerException ;

}
