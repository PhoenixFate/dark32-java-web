package com.phoenix.service;

import com.phoenix.pojo.ProductModel;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

public interface SearchService {

    List<ProductModel> searchProductList(String queryString, String catalog_name, String price, String sort) throws IOException, SolrServerException;

}
