package com.phoenix.search.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import java.io.IOException;

public class SolrTest {

    @Test
    public void addDocument() throws IOException, SolrServerException {
        //创建一个solrClient对象
        String baseUrl="http://47.99.113.229:19015/solr/ik_core";
        //创建 单机版 solr客户端连接
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(baseUrl).build();
        //创建一个solr文件对象 SolrInputDocuemnt
        SolrInputDocument solrInputDocument=new SolrInputDocument();
        //document添加field域
        //向文档域中添加数据，必须包含一个id域，所有的域的名称必须在 managed-schema中定义
        solrInputDocument.addField("id","test01");
        solrInputDocument.addField("item_title","title01");
        solrInputDocument.addField("item_price","100");

        //添加索引
        //把文档写入索引库
        httpSolrClient.add(solrInputDocument);
        //commit
        httpSolrClient.commit();

    }

    @Test
    public void deleteDocument() throws IOException, SolrServerException {
        //创建一个solrClient对象
        String baseUrl="http://47.99.113.229:19015/solr/ik_core";
        //创建 单机版 solr客户端连接
        HttpSolrClient httpSolrClient = new HttpSolrClient.Builder(baseUrl).build();
        //delete by id
        UpdateResponse updateResponse = httpSolrClient.deleteById("test01");
//        httpSolrClient.deleteByQuery("id:test01");
        httpSolrClient.commit();
    }



}
