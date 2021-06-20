package com.phoenix.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phoenix.common.jedis.JedisClient;
import com.phoenix.common.pojo.EasyUIDataGridResult;
import com.phoenix.common.utils.JsonUtils;
import com.phoenix.content.mapper.ContentMapper;
import com.phoenix.content.pojo.Content;
import com.phoenix.content.pojo.ContentExample;
import com.phoenix.content.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${APPLICATION_NAME}")
    private String APPLICATION_NAME;

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    @Override
    public EasyUIDataGridResult list(Long categoryId,Integer page,Integer rows) {
        //开启分页设置
        PageHelper.startPage(page,rows);
        ContentExample contentExample=new ContentExample();
        ContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<Content> contents = contentMapper.selectByExampleWithBLOBs(contentExample);

        //取分页信息
        PageInfo<Content> pageInfo=new PageInfo<>(contents);
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        easyUIDataGridResult.setRows(pageInfo.getList());
        return easyUIDataGridResult;
    }

    @Override
    public Integer create(Content content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        int count = contentMapper.insertSelective(content);
        //缓存同步，删除缓存中对应的数据
        //删除content对应的cid对应的list
        jedisClient.hdel(APPLICATION_NAME+CONTENT_LIST,content.getCategoryId()+"");
        return count;
    }

    @Override
    public Integer edit(Content content) {
        content.setUpdated(new Date());
        int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
        //缓存同步，删除缓存中对应的数据
        //删除content对应的cid对应的list
        jedisClient.hdel(APPLICATION_NAME+CONTENT_LIST,content.getCategoryId()+"");
        return count;
    }

    @Override
    public Integer delete(Long[] ids) {
        int count=0;
        for(Long id:ids){
            count += contentMapper.deleteByPrimaryKey(id);
        }
        //缓存同步，删除缓存中对应的数据
        jedisClient.hdel(APPLICATION_NAME+CONTENT_LIST);
        return count;
    }

    @Override
    public List<Content> getContentByCid(Long categoryId) {
        // 服务层添加缓存
        //查询缓存，缓存操纵不应该影响正常逻辑，所以要try-catch掉
        try {
            // 如果缓存中有，则直接返回
            String json = jedisClient.hget(APPLICATION_NAME + ":" + CONTENT_LIST, categoryId + "");
            if(StringUtils.isNotBlank(json)){
                return JsonUtils.jsonToList(json, Content.class);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        // 如果没有，则查询数据库，并且添加入缓存
        ContentExample contentExample=new ContentExample();
        ContentExample.Criteria criteria = contentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<Content> contentList = contentMapper.selectByExampleWithBLOBs(contentExample);
        //把结果添加入缓存
        try {
            jedisClient.hset(APPLICATION_NAME + ":" + CONTENT_LIST, categoryId + "", JsonUtils.objectToJson(contentList));
        } catch (Exception e){
            e.printStackTrace();
        }
        return contentList;
    }
}
