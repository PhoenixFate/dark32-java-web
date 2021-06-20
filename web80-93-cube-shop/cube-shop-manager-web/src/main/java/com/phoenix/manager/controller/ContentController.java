package com.phoenix.manager.controller;

import com.phoenix.common.pojo.EasyUIDataGridResult;
import com.phoenix.common.utils.CommonResult;
import com.phoenix.content.pojo.Content;
import com.phoenix.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("list")
    @ResponseBody
    public EasyUIDataGridResult list(@RequestParam(name = "categoryId") Long categoryId,@RequestParam(value="page") Integer page,@RequestParam(value="rows") Integer rows){
        EasyUIDataGridResult easyUIDataGridResult = contentService.list(categoryId, page, rows);
        return easyUIDataGridResult;
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(Content content){
        Integer integer = contentService.create(content);
        if(integer>=1){
            return CommonResult.ok();
        }else {
            return CommonResult.error("新增失败");
        }
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult edit(Content content){
        Integer integer = contentService.edit(content);
        if(integer>=1){
            return CommonResult.ok();
        }else {
            return CommonResult.error("修改失败");
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public CommonResult delete(Long[] ids){
        Integer integer = contentService.delete(ids);
        if(integer>=1){
            return CommonResult.ok();
        }else {
            return CommonResult.error("删除失败");
        }
    }
}
