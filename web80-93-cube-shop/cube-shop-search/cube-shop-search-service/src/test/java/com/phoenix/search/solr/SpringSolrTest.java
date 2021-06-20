package com.phoenix.search.solr;

import com.phoenix.common.pojo.SearchItem;
import com.phoenix.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SpringSolrTest {

    @Test
    public void temp(){
        //初始化spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-solr.xml");
        //从容器中获得JedisClient对象
        Object optional1 = applicationContext.getBean("optional");
        System.out.println(optional1);
        Optional optional= Optional.of("/");
        System.out.println(optional);
        System.out.println(optional.getClass());
        System.out.println(optional.get());
//        Optional optional= (Optional) applicationContext.getBean("optional");
//        System.out.println(optional);
//        System.out.println(optional.get());
    }

    @Test
    public void test01() throws IOException, SolrServerException {
        //初始化spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-solr.xml");
        //从容器中获得JedisClient对象
        SolrClient httpSolrClient=applicationContext.getBean(SolrClient.class);
        System.out.println(httpSolrClient);
        SolrQuery solrQuery=new SolrQuery("*:*");
        QueryResponse queryResponse = httpSolrClient.query(solrQuery);
        //文档结果集
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //总条数
        long numFound = solrDocumentList.getNumFound();
        System.out.println("总条数： "+numFound);

    }

    @Test
    public void test02() throws IOException, SolrServerException {
        //初始化spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-solr.xml");
        //从容器中获得JedisClient对象
        SolrClient cloudSolrClient=applicationContext.getBean(SolrClient.class);
        System.out.println(cloudSolrClient);
        SolrQuery solrQuery=new SolrQuery();
        solrQuery.setQuery("手机");
        // 设置分页条件

        solrQuery.setStart(0);
        solrQuery.setRows(20);
        // 设置默认搜索域
        solrQuery.set("df","item_title");
        // 开启高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style='color:red'>");
        solrQuery.setHighlightSimplePost("</em>");


        SearchResult searchResult=new SearchResult();

        //根据query查询索引
        QueryResponse queryResponse = cloudSolrClient.query(solrQuery);
        //取结果记录： 总记录数，取商品列表，取高亮显示
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //总条数
        long numFound = solrDocumentList.getNumFound();
        searchResult.setRecordCount(numFound);
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        List<SearchItem> searchItemList=new ArrayList<>();
        for(SolrDocument solrDocument:solrDocumentList){
            SearchItem searchItem=new SearchItem();
            Object id = solrDocument.get("id");
            Object item_price = solrDocument.get("item_price");
            Object item_category_name = solrDocument.get("item_category_name");
            Object item_image = solrDocument.get("item_image");
            Object item_sell_point = solrDocument.get("item_sell_point");

            searchItem.setId(id.toString());
            searchItem.setPrice(item_price.toString());
            searchItem.setCategoryName(item_category_name.toString());
            searchItem.setImage(item_image.toString());
            searchItem.setSellPoint(item_sell_point.toString());

            Map<String, List<String>> stringListMap = highlighting.get(id.toString());
            List<String> highlightValue = stringListMap.get("item_title");
            if(highlightValue!=null && highlightValue.size()>0){
                String title = highlightValue.get(0);
                searchItem.setTitle(title);
            }else {
                Object item_title = solrDocument.get("item_title");
                searchItem.setTitle(item_title.toString());
            }
            searchItemList.add(searchItem);
        }
        searchResult.setItemList(searchItemList);


    }


}
