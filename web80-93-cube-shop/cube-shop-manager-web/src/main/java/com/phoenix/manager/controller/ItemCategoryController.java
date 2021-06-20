package com.phoenix.manager.controller;

import com.phoenix.common.pojo.EasyUITreeNode;
import com.phoenix.item.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("list")
    @ResponseBody
    public List<EasyUITreeNode> list(@RequestParam(name = "id",defaultValue = "0") Long parentId){
        List<EasyUITreeNode> easyUITreeNodeList = itemCategoryService.getListByParentId(parentId);
        return easyUITreeNodeList;
    }



}
