package com.phoenix.manager.controller;

import com.phoenix.common.utils.CommonResult;
import com.phoenix.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 导入商品到索引库
 */

@Controller
@RequestMapping("search")
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping(value = "item/import",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult importAllItems(){
        CommonResult commonResult = searchItemService.importAllItems();
        return commonResult;
    }


}
