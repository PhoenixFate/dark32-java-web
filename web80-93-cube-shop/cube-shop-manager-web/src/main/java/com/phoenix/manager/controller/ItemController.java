package com.phoenix.manager.controller;

import com.phoenix.common.pojo.EasyUIDataGridResult;
import com.phoenix.item.pojo.Item;
import com.phoenix.item.service.ItemService;
import com.phoenix.common.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/info/{id}")
    @ResponseBody //@ResponseBody 需要依赖jackson包
    public Item info(@PathVariable Long id){
        Item item = itemService.getItemById(id);
        return item;
    }

    //@RequestParam  如果value值和参数名 相同； 则可以省略@RequestParam
    //value：表示参数名，即前端页面传过来的参数名
    //defaultValue：参数默认值，如果设置了该值，required=true将失效，自动为false,如果没有传该参数，就使用默认值
    //required：表示是否要强制包含该参数，默认值为false,表示允许请求中不包含该参数，并且该参数值会为设为null。true表示该请求中必须包含该参数否则报错
    @RequestMapping("list")
    @ResponseBody
    public EasyUIDataGridResult list(@RequestParam(value="page") Integer page,@RequestParam(value="rows") Integer rows){
        EasyUIDataGridResult easyUIDataGridResult = itemService.getItemList(page, rows);
        return easyUIDataGridResult;
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addItem(Item item, String desc){
        CommonResult commonResult = itemService.addItem(item, desc);
        return commonResult;
    }

}
