package com.phoenix.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 通过solrJ管理索引
 * 添加
 * 删除
 * 修改
 *
 * 查询（搜索）
 */
public class SolrJManager {

    /**
     * 添加索引
     */
    @Test
    public void testAddIndex() throws IOException, SolrServerException {
        String baseUrl="http://47.99.113.229:19015/solr/collection1";
        //创建 单机版 solr客户端连接
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(baseUrl).build();
        //创建document
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        //document添加field域
        solrInputDocument.addField("id","test01");
        solrInputDocument.addField("name","one");
        //添加索引
        httpSolrClient.add(solrInputDocument);
        httpSolrClient.commit();
    }

    /**
     * 删除索引
     */
    @Test
    public void testDeleteIndex() throws IOException, SolrServerException {
        String baseUrl="http://47.99.113.229:19015/solr/collection1";
        //创建 单机版 solr客户端连接
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(baseUrl).build();
        //删除全部
        httpSolrClient.deleteByQuery("*:*");
        httpSolrClient.commit();
    }

    @Test
    public void testUpdateIndex() throws IOException, SolrServerException {
        String baseUrl="http://47.99.113.229:19015/solr/collection1";
        //创建 单机版 solr客户端连接
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(baseUrl).build();
        //更新
        //只要id相同就是更新，id不同就是添加
        //solr：如果id存在，先删除后添加
        //创建document
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        solrInputDocument.addField("id","test01");
//        solrInputDocument.addField("name","two");
        solrInputDocument.addField("title","testTitle");
        httpSolrClient.add(solrInputDocument);
        httpSolrClient.commit();
    }

    @Test
    public void testSearchIndex() throws IOException, SolrServerException {
        //单机版solr
//        String baseUrl="http://47.99.113.229:19015/solr/collection1";
//        //创建 单机版 solr客户端连接
//        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(baseUrl).build();

        // 集群版solr
        List<String> zkHosts = new ArrayList<String>();
        zkHosts.add("47.99.113.229:2191");
        zkHosts.add("47.99.113.229:2192");
        zkHosts.add("47.99.113.229:2193");
        zkHosts.add("47.99.113.229:2194");
        zkHosts.add("47.99.113.229:2195");
        Optional<String> zkChroot = Optional.of("/");
        //builder的构造函数需要一个List和一个Optional
        CloudSolrClient.Builder builder = new CloudSolrClient.Builder(zkHosts, zkChroot);

        CloudSolrClient solrClient = builder.build();
        solrClient.setDefaultCollection("collection1");

        solrClient.setZkConnectTimeout(60*1000);

        //查询 关键字 台灯；过滤条件："product_catalog_name":"幽默杂货", "product_price":18.9, 价格排序  分页 开始行 每页数  高亮 默认域  只查指定域
        SolrQuery solrQuery=new SolrQuery();
        //设置关键字
        //默认只查前10条
//        solrQuery.set("q","product_name:幸福");
        solrQuery.setQuery("product_name:幸福");
        //设置过滤条件
        solrQuery.set("fq","product_catalog_name:幽默杂货");
        //价格区间
        solrQuery.set("fq","product_price:[* TO 20 ]");
//        //价格排序
        solrQuery.addSort("product_price", SolrQuery.ORDER.desc);
//        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(5);
//        //默认域
        solrQuery.set("df","product_name");
//        //设置显示的查询域
        //solrQuery.set("fl","id,product_name");

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
//        QueryResponse queryResponse = httpSolrClient.query(solrQuery);
        QueryResponse queryResponse = solrClient.query(solrQuery);

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
            String productNameHight = highlighting.get(id).get("product_name").get(0);
            System.out.println("height light: "+productNameHight);
        }

    }

}
