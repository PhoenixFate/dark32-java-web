package com.phoenix.manager.controller;

import com.phoenix.common.pojo.EasyUITreeNode;
import com.phoenix.common.utils.CommonResult;
import com.phoenix.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("list")
    @ResponseBody
    public List<EasyUITreeNode> list(@RequestParam(name = "id", required = false , defaultValue = "0") Long id){
        List<EasyUITreeNode> easyUITreeNodeList = contentCategoryService.getListByParentId(id);
        return easyUITreeNodeList;
    }

    @RequestMapping(value="create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestParam(name = "name", required = true ) String name, @RequestParam(name="parentId",required = true)Long parentId){
        CommonResult commonResult = contentCategoryService.create(name, parentId);
        return commonResult;
    }

    @RequestMapping(value="update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestParam(name = "name", required = true ) String name, @RequestParam(name="id",required = true)Long id){
        Integer count= contentCategoryService.update(name, id);
        if(count>=1){
            return CommonResult.ok();
        }else {
            return CommonResult.error("删除失败");
        }
    }


    @RequestMapping(value="delete")
    @ResponseBody
    public CommonResult delete(@RequestParam(name="id",required = true)Long id){
        Integer count = contentCategoryService.delete(id);
        if(count>=1){
            return CommonResult.ok();
        }else {
            return CommonResult.error("删除失败");
        }
    }

}
