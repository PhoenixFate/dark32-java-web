package com.phoenix.search.service.impl;

import com.phoenix.common.pojo.SearchItem;
import com.phoenix.common.utils.CommonResult;
import com.phoenix.search.mapper.SearchItemMapper;
import com.phoenix.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public CommonResult importAllItems() {
        try {
            //查询商品列表
            List<SearchItem> itemList = searchItemMapper.getItemList();
            //遍历商品列表
            for (SearchItem searchItem:
                    itemList) {
                //创建文档对象
                SolrInputDocument solrInputDocument =new SolrInputDocument();
                //向文档对象中添加域
                solrInputDocument.addField("id",searchItem.getId());
                solrInputDocument.addField("item_title",searchItem.getTitle());
                solrInputDocument.addField("item_sell_point",searchItem.getSellPoint());
                solrInputDocument.addField("item_price",searchItem.getPrice());
                solrInputDocument.addField("item_image",searchItem.getImage());
                solrInputDocument.addField("item_category_name",searchItem.getCategoryName());
                //把文档对象写入索引库
                solrClient.add(solrInputDocument);
            }
            //提交
            solrClient.commit();
            return CommonResult.ok();
        } catch (Exception e){
            return CommonResult.error("服务器导入索引库发生异常");
        }
    }
}
