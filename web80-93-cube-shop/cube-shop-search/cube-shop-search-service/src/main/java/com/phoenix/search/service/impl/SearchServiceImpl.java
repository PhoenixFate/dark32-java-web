package com.phoenix.search.service.impl;

import com.phoenix.common.pojo.SearchResult;
import com.phoenix.search.dao.SearchItemDao;
import com.phoenix.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchItemDao searchItemDao;

    @Override
    public SearchResult searchItem(String keyword, Integer page, Integer rows) {
        // 创建一个 SolrQuery 对象
        SolrQuery solrQuery=new SolrQuery();
        // 设置查询条件
        solrQuery.setQuery(keyword);
        // 设置分页条件
        if(page<=0){
            page=1;
        }
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        // 设置默认搜索域
        solrQuery.set("df","item_title");
        // 开启高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style='color:red'>");
        solrQuery.setHighlightSimplePost("</em>");

        SearchResult search=null;
        // 使用dao进行计算
        try {
            search = searchItemDao.search(solrQuery);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        // 计算总页数
        Long recordCount = search.getRecordCount();
        Integer totalPages= (int) Math.ceil (recordCount*1.0/rows);
        search.setTotalPages(totalPages);
        //返回 SearchResult 结果
        return search;
    }
}
