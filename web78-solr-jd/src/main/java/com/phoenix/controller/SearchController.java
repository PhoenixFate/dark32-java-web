package com.phoenix.controller;

import com.phoenix.pojo.ProductModel;
import com.phoenix.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    //商品列表
    @RequestMapping("list.action")
    public String productList(String queryString, String catalog_name, String price, String sort, Model model){
        //通过上面的四个条件查询对应的商品结果集
        List<ProductModel> productModelList=null;
        try {
            productModelList = searchService.searchProductList(queryString, catalog_name, price, sort);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        model.addAttribute("queryString",queryString);
        model.addAttribute("productList",productModelList);
        model.addAttribute("catalog_name",catalog_name);
        model.addAttribute("price",price);
        model.addAttribute("sort",sort);
        return "product_list";
    }


}
