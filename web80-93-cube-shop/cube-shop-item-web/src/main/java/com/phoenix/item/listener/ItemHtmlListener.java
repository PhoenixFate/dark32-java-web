package com.phoenix.item.listener;

import com.phoenix.item.dto.ItemDto;
import com.phoenix.item.pojo.Item;
import com.phoenix.item.pojo.ItemDescription;
import com.phoenix.item.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 监听商品的添加，添加商品，生成对应的html静态页
 */
public class ItemHtmlListener implements MessageListener {

    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${HTML_FREEMARKER_PATH}")
    private String HTML_FREEMARKER_PATH;

    @Override
    public void onMessage(Message message) {
        try {
            //创建模板，参考jsp页面来创建
            //从消息中取商品id
            TextMessage textMessage=(TextMessage) message;
            //根据商品id，查询商品基本信息，商品描述信息
            Long itemId = new Long(textMessage.getText());
            //查询商品之前sleep一下，等待事务提交
            Thread.sleep(1000);
            Item item = itemService.getItemById(itemId);
            ItemDto itemDto=new ItemDto();
            itemDto.setItem(item);
            itemDto.setImages(item.getImage().split(","));
            ItemDescription itemDescription = itemService.getItemDescriptionById(itemId);
            itemDto.setItemDescription(itemDescription);

            //创建数据集，把商品信息封装进去
            Map<String,Object> map=new HashMap<>();
            map.put("itemDto",itemDto);

            //加载模板对象
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            //加载模板
            Template template = configuration.getTemplate("item.ftl");

            //创建一个输出流，指定输出的目录及文件名
            //指定文件输出路径及文件名
            Writer writer=new FileWriter(new File(HTML_FREEMARKER_PATH+"/item_"+itemId+".html"));
            //生成静态页面
            template.process(map,writer);
            //关闭流
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
