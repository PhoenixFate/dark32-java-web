package com.phoenix.item.service.impl;

import com.phoenix.item.mapper.ItemCategoryMapper;
import com.phoenix.common.pojo.EasyUITreeNode;
import com.phoenix.item.pojo.ItemCategory;
import com.phoenix.item.pojo.ItemCategoryExample;
import com.phoenix.item.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Override
    public List<EasyUITreeNode> getListByParentId(Long id) {
        ItemCategoryExample itemCategoryExample=new ItemCategoryExample();
        ItemCategoryExample.Criteria criteria = itemCategoryExample.createCriteria();
        // 设置查询条件
        criteria.andParentIdEqualTo(id);
        List<ItemCategory> itemCategories = itemCategoryMapper.selectByExample(itemCategoryExample);
        // 把List<ItemCategory> 转成 List<EasyUITreeNode>
        List<EasyUITreeNode> easyUITreeNodeList=new ArrayList<>();
        for(ItemCategory itemCategory:itemCategories){
            EasyUITreeNode easyUITreeNode=new EasyUITreeNode();
            easyUITreeNode.setId(itemCategory.getId());
            easyUITreeNode.setText(itemCategory.getName());
            if(itemCategory.getIsParent()){
                easyUITreeNode.setState("closed");
            }else {
                easyUITreeNode.setState("open");
            }
            easyUITreeNodeList.add(easyUITreeNode);
        }
        return easyUITreeNodeList;
    }
}
