package com.phoenix.search.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SolrCloudTest {

    private  CloudSolrClient cloudSolrClient;

    @Before
    public void initSolrCloud(){
        List<String> zkHosts=new ArrayList<>();
        zkHosts.add("47.99.113.229:2191");
        zkHosts.add("47.99.113.229:2192");
        zkHosts.add("47.99.113.229:2193");
        zkHosts.add("47.99.113.229:2194");
        zkHosts.add("47.99.113.229:2195");
//        Optional<String> zkChroot = Optional.of("/");
//        Optional optional=new Optional("/");
        Optional<String> zkChroot = Optional.empty();
        //builder的构造函数需要一个List和一个Optional
        CloudSolrClient.Builder builder = new CloudSolrClient.Builder(zkHosts, zkChroot);
        cloudSolrClient = builder.build();
        cloudSolrClient.setZkConnectTimeout(60*1000);
        cloudSolrClient.setDefaultCollection("collection2");
    }

    @Test
    public void testAddDocument() throws IOException, SolrServerException {
        //创建一个solr文件对象 SolrInputDocuemnt
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        //document添加field域
        //向文档域中添加数据，必须包含一个id域，所有的域的名称必须在 managed-schema中定义
        solrInputDocument.addField("id","cloud01");
        solrInputDocument.addField("item_title","cloudTitle01");
        solrInputDocument.addField("item_price","200");
        //添加索引
        //把文档写入索引库
        cloudSolrClient.add(solrInputDocument);
        //commit
        cloudSolrClient.commit();
    }

    @Test
    public void testDeleteDocument() throws IOException, SolrServerException {
        //添加索引
        //把文档写入索引库
        cloudSolrClient.deleteById("cloud01");
        //commit
        cloudSolrClient.commit();
        String str=new String("sss");
    }

    @Test
    public void testSearchSimple() throws IOException, SolrServerException {
        //创建一个查询对象 SolrQuery对象
        SolrQuery solrQuery=new SolrQuery();
        //设置查询条件
        //主查询条件
//        solrQuery.setQuery("*:*");
        solrQuery.set("q","*:*");

        //执行查询，获得QueryResponse对象
        QueryResponse queryResponse = cloudSolrClient.query(solrQuery);
        //通过QueryResponse对象获得文档列表以及 总记录数
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        long numFound = solrDocumentList.getNumFound();
        System.out.println("总条数： "+numFound);
        //遍历文档列表，从文档中取域到值
        for(SolrDocument solrDocument:solrDocumentList){
            Object id = solrDocument.get("id");
            System.out.println(id);
            Object item_title = solrDocument.get("item_title");
            System.out.println(item_title);
            Object item_sell_point = solrDocument.get("item_sell_point");
            System.out.println(item_sell_point);
            Object item_price = solrDocument.get("item_price");
            System.out.println(item_price);
            Object item_image = solrDocument.get("item_image");
            System.out.println(item_image);
        }
    }


    @Test
    public void testSearchComplex() throws IOException, SolrServerException {
        //创建一个查询对象 SolrQuery对象
        SolrQuery solrQuery=new SolrQuery();
        //设置查询条件
        //主查询条件
//        solrQuery.setQuery("*:*");
//        solrQuery.set("q","*:*");
        solrQuery.setQuery("手机");

        //分页
        solrQuery.setStart(10);
        solrQuery.setRows(5);

        //设置默认搜索域
        solrQuery.set("df","item_title");

        //设置高亮
        //打开高亮
        solrQuery.setHighlight(true);
        //设置高亮显示到域
        solrQuery.addHighlightField("item_title");
        //高亮显示的前缀
        solrQuery.setHighlightSimplePre("<<<<<");
        //高亮显示到后缀
        solrQuery.setHighlightSimplePost(">>>>>");

        //执行查询，获得QueryResponse对象
        QueryResponse queryResponse = cloudSolrClient.query(solrQuery);
        //高亮的返回值
        //第一层是id
        //第二层： key: 高亮显示的域的名称
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        //通过QueryResponse对象获得文档列表以及 总记录数
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        long numFound = solrDocumentList.getNumFound();
        System.out.println("总条数： "+numFound);
        //遍历文档列表，从文档中取域到值
        for(SolrDocument solrDocument:solrDocumentList){
            Object id = solrDocument.get("id");
            System.out.println(id);
            Object item_title = solrDocument.get("item_title");
            System.out.println(item_title);
            Object item_sell_point = solrDocument.get("item_sell_point");
            System.out.println(item_sell_point);
            Object item_price = solrDocument.get("item_price");
            System.out.println(item_price);
            Object item_image = solrDocument.get("item_image");
            System.out.println(item_image);

            Map<String, List<String>> stringListMap = highlighting.get(id.toString());
            List<String> highlightValues = stringListMap.get("item_title");
            if(highlightValues!=null && highlightValues.size()>0){
                for(String highlight:highlightValues){
                    System.out.println("高亮显示的内容： "+highlight);
                }
            }

        }




    }
}
