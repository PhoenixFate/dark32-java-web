package com.phoenix.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phoenix.common.jedis.JedisClient;
import com.phoenix.common.utils.JsonUtils;
import com.phoenix.item.mapper.ItemDescriptionMapper;
import com.phoenix.item.mapper.ItemMapper;
import com.phoenix.common.pojo.EasyUIDataGridResult;
import com.phoenix.item.pojo.Item;
import com.phoenix.item.pojo.ItemDescription;
import com.phoenix.item.pojo.ItemExample;
import com.phoenix.item.service.ItemService;
import com.phoenix.common.utils.CommonResult;
import com.phoenix.common.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescriptionMapper itemDescriptionMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name = "itemAddTopic")
    private Destination itemAddTopic;
    @Autowired
    private JedisClient jedisClient;
    @Value("${APPLICATION_NAME}")
    private String APPLICATION_NAME;
    @Value("${ITEM_INFO}")
    private String ITEM_INFO;
    @Value("${ITEM_BASE}")
    private String ITEM_BASE;
    @Value("${ITEM_DESC}")
    private String ITEM_DESC;
    @Value("${EXPIRE_TIME}")
    private Integer EXPIRE_TIME;

    @Override
    public Item getItemById(Long id) {
        //查询缓存
        try {
            //缓存业务不应该影响正常流程
            String itemJson = jedisClient.get(APPLICATION_NAME + ":" + ITEM_INFO + ":" + id + ":" + ITEM_BASE);
            if(StringUtils.isNotBlank(itemJson)){
                System.out.println("-------------从缓存中获得该商品：-----------"+itemJson);
                Item item = JsonUtils.jsonToPojo(itemJson, Item.class);
                return item;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        //缓存中没有，则查询数据库
        System.out.println("debug1");
        Item item = itemMapper.selectByPrimaryKey(id);
        if(item!=null){
            //添加item到缓存中
            try {
                //缓存业务不应该影响正常流程
                jedisClient.set(APPLICATION_NAME+":"+ITEM_INFO+":"+item.getId()+":"+ITEM_BASE, JsonUtils.objectToJson(item));
                //设置过期时间
                jedisClient.expire(APPLICATION_NAME+":"+ITEM_INFO+":"+item.getId()+":"+ITEM_BASE,EXPIRE_TIME);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        System.out.println("debug2s");
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //执行查询
        List<Item> itemList = itemMapper.selectByExample(new ItemExample());
        //取分页信息
        PageInfo<Item> pageInfo=new PageInfo<>(itemList);

        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        easyUIDataGridResult.setRows(pageInfo.getList());
        return easyUIDataGridResult;
    }

    @Override
    public CommonResult addItem(Item item, String desc) {
        // 生成商品id
        Long id=IDUtils.genItemId();
        item.setId(id);
        //补全item属性
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向商品表插入数据
        itemMapper.insert(item);

        //创建一个ItemDesc对象
        ItemDescription itemDescription=new ItemDescription();
        itemDescription.setCreated(new Date());
        itemDescription.setUpdated(new Date());
        itemDescription.setItemDesc(desc);
        itemDescription.setItemId(id);
        //向商品表述表插入数据
        itemDescriptionMapper.insert(itemDescription);

        //理论上需要等待新增商品到事务提交之后，再发送消息
        //发送消息的代码可以在addItem的表现层
        //获得接受消息的listener处 Thread.sleep(1000)
        //发送一个通知消息
        jmsTemplate.send(itemAddTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(id + "");
                return textMessage;
            }
        });


        //返回成功
        CommonResult result = CommonResult.ok();
        return result;
    }

    @Override
    public ItemDescription getItemDescriptionById(Long id) {
        //查询缓存
        try {
            //缓存业务不应该影响正常流程
            String itemDescJson = jedisClient.get(APPLICATION_NAME + ":" + ITEM_INFO + ":" + id + ":" + ITEM_DESC);
            if(StringUtils.isNotBlank(itemDescJson)){
                //System.out.println("-------------从缓存中获得该商品描述：-----------"+itemDescJson);
                ItemDescription itemDescription = JsonUtils.jsonToPojo(itemDescJson, ItemDescription.class);
                return itemDescription;
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        ItemDescription itemDescription = itemDescriptionMapper.selectByPrimaryKey(id);
        if(itemDescription!=null){
            //添加item到缓存中
            try {
                //缓存业务不应该影响正常流程
                jedisClient.set(APPLICATION_NAME+":"+ITEM_INFO+":"+itemDescription.getItemId()+":"+ITEM_DESC, JsonUtils.objectToJson(itemDescription));
                //设置过期时间
                jedisClient.expire(APPLICATION_NAME+":"+ITEM_INFO+":"+itemDescription.getItemId()+":"+ITEM_DESC,EXPIRE_TIME);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return itemDescription;
    }
}
