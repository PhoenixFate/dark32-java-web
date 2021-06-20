package com.phoenix.search.dao;

import com.phoenix.common.pojo.SearchItem;
import com.phoenix.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
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
public class SearchItemDao {

    @Autowired
    private SolrClient solrClient;

    /**
     * 根据查询条件查询商品
     * @param solrQuery
     * @return
     */
    public SearchResult search(SolrQuery solrQuery) throws IOException, SolrServerException {
        SearchResult searchResult=new SearchResult();

        //根据query查询索引
        QueryResponse queryResponse = solrClient.query(solrQuery);
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
            if(item_category_name!=null){
                searchItem.setCategoryName(item_category_name.toString());
            }
            if(item_image!=null){
                searchItem.setImage(item_image.toString());
            }
            if(item_sell_point!=null){
                searchItem.setSellPoint(item_sell_point.toString());
            }
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
        return searchResult;
    }

}
