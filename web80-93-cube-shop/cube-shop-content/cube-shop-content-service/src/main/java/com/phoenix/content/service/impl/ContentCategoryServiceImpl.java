package com.phoenix.content.service.impl;

import com.phoenix.common.pojo.EasyUITreeNode;
import com.phoenix.common.utils.CommonResult;
import com.phoenix.content.mapper.ContentCategoryMapper;
import com.phoenix.content.pojo.ContentCategory;
import com.phoenix.content.pojo.ContentCategoryExample;
import com.phoenix.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode>  getListByParentId(Long id) {
        ContentCategoryExample contentCategoryExample=new ContentCategoryExample();
        ContentCategoryExample.Criteria criteria = contentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<ContentCategory> contentCategoryList = contentCategoryMapper.selectByExample(contentCategoryExample);
        // 把List<ItemCategory> 转成 List<EasyUITreeNode>
        List<EasyUITreeNode> easyUITreeNodeList=new ArrayList<>();
        for(ContentCategory contentCategory:contentCategoryList){
            EasyUITreeNode easyUITreeNode=new EasyUITreeNode();
            easyUITreeNode.setId(contentCategory.getId());
            easyUITreeNode.setText(contentCategory.getName());
            if(contentCategory.getIsParent()){
                easyUITreeNode.setState("closed");
            }else {
                easyUITreeNode.setState("open");
            }
            easyUITreeNodeList.add(easyUITreeNode);
        }
        return easyUITreeNodeList;
    }

    @Override
    public CommonResult create(String name, Long parentId) {
        ContentCategory contentCategory=new ContentCategory();
        // false；叶子节点
        contentCategory.setIsParent(false);
        // 默认排序
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setName(name);
        //1正常  2删除
        contentCategory.setStatus(1);
        contentCategory.setUpdated(new Date());
        contentCategory.setParentId(parentId);
        // 主键生成策略，会自动注入contentCategory
        contentCategoryMapper.insertSelective(contentCategory);
        //判断父节点是否是 isParent，如果是，保持，如果false，改成true
        ContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
        if(!parentContentCategory.getIsParent()){
            parentContentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentContentCategory);
        }
        CommonResult commonResult=CommonResult.ok(contentCategory);
        return commonResult;
    }

    @Override
    public Integer delete(Long id) {
        ContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        int count = contentCategoryMapper.deleteByPrimaryKey(id);
        ContentCategoryExample contentCategoryExample=new ContentCategoryExample();
        ContentCategoryExample.Criteria criteria = contentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(contentCategory.getParentId());
        List<ContentCategory> contentCategories = contentCategoryMapper.selectByExample(contentCategoryExample);
        if(contentCategories.size()==0){
            //修改父节点的isParent为false
            ContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
            parentContentCategory.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKeySelective(parentContentCategory);
        }
        return count;
    }

    @Override
    public Integer update(String name, Long id) {
        ContentCategory contentCategory=new ContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(name);
        int count = contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        return count;
    }

}
