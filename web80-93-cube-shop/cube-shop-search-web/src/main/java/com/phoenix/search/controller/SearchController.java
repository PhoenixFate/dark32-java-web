package com.phoenix.search.controller;

import com.phoenix.common.pojo.SearchResult;
import com.phoenix.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品搜索controller
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Value("${SEARCH_RESULT_ROWS}")
    private Integer SEARCH_RESULT_ROWS;

    @Value("${ITEM_ADDRESS}")
    private String ITEM_ADDRESS;

    @Value("${PORTAL_ADDRESS}")
    private String PORTAL_ADDRESS;

    @Value("${SSO_ADDRESS}")
    private String SSO_ADDRESS;

    @RequestMapping("item")
    public String searchItem(String keyword, @RequestParam(name = "page",defaultValue = "1") Integer page, Model model){
        SearchResult searchResult = searchService.searchItem(keyword, page, SEARCH_RESULT_ROWS);
        //把结果传递给页面
        model.addAttribute("query",keyword);
        model.addAttribute("totalPages",searchResult.getTotalPages());
        model.addAttribute("page",page);
        model.addAttribute("recordCount",searchResult.getRecordCount());
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("itemAddress",ITEM_ADDRESS);
        model.addAttribute("portalAddress",PORTAL_ADDRESS);
        model.addAttribute("ssoAddress",SSO_ADDRESS);
        model.addAttribute("registerAddress",SSO_ADDRESS+"/user/register/page");
        //异常测试
        //int a=1/0;

        //返回逻辑视图
        return "search";
    }



}
