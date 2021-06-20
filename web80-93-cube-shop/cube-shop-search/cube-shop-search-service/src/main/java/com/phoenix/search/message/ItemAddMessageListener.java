package com.phoenix.search.message;

import com.phoenix.common.pojo.SearchItem;
import com.phoenix.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * 监听商品添加消息
 * 接受消息后，将对应的商品信息同步到索引库
 *
 */
public class ItemAddMessageListener implements MessageListener {

    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SolrClient solrClient;


    @Override
    public void onMessage(Message message) {
        try {
            //接收到消息
            //从消息中取商品id
            TextMessage textMessage=(TextMessage) message;
            String text = textMessage.getText();
            Long id=new Long(text);
            //等待事务到提交
            Thread.sleep(1000);

            //根据商品id查询商品信息
            SearchItem searchItem = searchItemMapper.getItemById(id);
            //创建文档对象，向文档对象中添加域
            SolrInputDocument solrInputDocument=new SolrInputDocument();
            //向文档对象中添加域
            solrInputDocument.addField("id",searchItem.getId());
            solrInputDocument.addField("item_title",searchItem.getTitle());
            solrInputDocument.addField("item_sell_point",searchItem.getSellPoint());
            solrInputDocument.addField("item_price",searchItem.getPrice());
            solrInputDocument.addField("item_image",searchItem.getImage());
            solrInputDocument.addField("item_category_name",searchItem.getCategoryName());
            //把文档写入索引库
            solrClient.add(solrInputDocument);

            //提交
            solrClient.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
