package com.phoenix.dao;

import com.phoenix.pojo.ProductModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDaoImpl implements SearchDao {

    //索引库
    @Autowired
    private HttpSolrClient httpSolrClient;

    @Override
    public List<ProductModel> searchProductList(String queryString, String catalog_name, String price, String sort) throws IOException, SolrServerException {
        System.out.println("queryString:   "+queryString);
        System.out.println("catalog_name:      "+catalog_name);
        System.out.println("price:     " +price);
        System.out.println("sort:    "+sort);
        //查询 关键字 台灯；过滤条件："product_catalog_name":"幽默杂货", "product_price":18.9, 价格排序  分页 开始行 每页数  高亮 默认域  只查指定域
        SolrQuery solrQuery=new SolrQuery("*:*");
        //设置关键字
        //默认只查前10条
//        solrQuery.set("q","product_name:幸福");
//        solrQuery.setQuery("product_name:"+queryString);
        solrQuery.setQuery(queryString);//默认去搜默认域
        //设置过滤条件
        if(StringUtils.isNotEmpty(catalog_name)){
            solrQuery.set("fq","product_catalog_name:"+catalog_name);
        }
        //价格区间
        if(StringUtils.isNotEmpty(price)){
            //price: *-9 50-*
            String[] split = price.split("-");
            solrQuery.set("fq","product_price:["+split[0]+" TO "+split[1]+" ]");
        }
//        //价格排序
        if("1".equals(sort)){
            System.out.println("1 equals");
            solrQuery.addSort("product_price", SolrQuery.ORDER.desc);
        }else {
            System.out.println("1 equals else");
            solrQuery.addSort("product_price", SolrQuery.ORDER.asc);
        }

//        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(16);
//        //默认域
        solrQuery.set("df","product_keywords");
//        //设置显示的查询域
        solrQuery.set("fl","id,product_name,product_price,product_picture,product_catalog_name");

        //高亮
        //开启高亮开关
        solrQuery.setHighlight(true);
        //设置高亮域
        solrQuery.addHighlightField("product_name");
        //设置高亮前缀
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        //设置高亮后缀
        solrQuery.setHighlightSimplePost("</span>");

        //执行查询
        QueryResponse queryResponse = httpSolrClient.query(solrQuery);
        //文档结果集
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //总条数
        long numFound = solrDocumentList.getNumFound();
        System.out.println("总条数： "+numFound);

        //高亮的返回值
        // 第一个map key: id , value: 第二个map
        // 第二个map key: 域名，value：list
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        //每一条内容详情
        /**
         *   {
         *         "product_catalog_name":"幽默杂货",
         *         "product_price":18.9,
         *         "product_name":"幸福一家人彩色金属门后挂&nbsp;8钩免钉门背挂钩2088",
         *         "id":"2",
         *         "product_picture":"2014032612461139.png",
         *         "_version_":1649976961100939264
         *    },
         */

        List<ProductModel> productModelList=new ArrayList<ProductModel>();

        for(SolrDocument solrDocument:solrDocumentList){
            Object id = solrDocument.getFieldValue("id");
            Object product_name = solrDocument.getFieldValue("product_name");
            Object product_catalog_name = solrDocument.getFieldValue("product_catalog_name");
            Object product_price = solrDocument.getFieldValue("product_price");
            Object product_picture = solrDocument.getFieldValue("product_picture");
            Object version_ = solrDocument.getFieldValue("_version_");
            System.out.println("id:  "+id);
            System.out.println("product_catalog_name: "+product_catalog_name);
            System.out.println("product_name: "+product_name);
            System.out.println("product_price: "+product_price);
            System.out.println("product_picture: "+product_picture);
            System.out.println("_version_: "+version_);
//            String productNameHight = highlighting.get(id).get("product_name").get(0);
//            System.out.println("height light: "+productNameHight);
            Map<String, List<String>> highlightingById = highlighting.get(id.toString());
            System.out.println("高亮");
            System.out.println(highlightingById);
            String highlightingName=null;
            if(highlightingById!=null){
                List<String> productNameList = highlightingById.get("product_name");
                highlightingName = productNameList.get(0);
            }
            ProductModel productModel=new ProductModel();
            productModel.setPid(id.toString());
            productModel.setCatalog_name(product_catalog_name.toString());
            productModel.setName(highlightingName!=null?highlightingName:product_name.toString());
            productModel.setPicture(product_picture.toString());
            productModel.setPrice((Float)product_price);
            productModelList.add(productModel);
        }

        return productModelList;
    }

}
