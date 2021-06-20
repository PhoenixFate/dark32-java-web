package com.phoenix.portal.controller;

import com.phoenix.content.pojo.Content;
import com.phoenix.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @Value("${CONTENT_CAROUSEL_ID}")
    private Long CONTENT_CAROUSEL_ID;

    @Value("${SEARCH_ADDRESS}")
    private String SEARCH_ADDRESS;

    @Value("${SSO_ADDRESS}")
    private String SSO_ADDRESS;

    @Value("${PORTAL_ADDRESS}")
    private String PORTAL_ADDRESS;

    @Value("${CART_ADDRESS}")
    private String CART_ADDRESS;


    @RequestMapping("index")
    public String showIndex(Model model){
        // 轮播图
        List<Content> bigAdsList = contentService.getContentByCid(CONTENT_CAROUSEL_ID);
        // 把结果传递给页面
        model.addAttribute("bigAdsList",bigAdsList);
        model.addAttribute("searchAddress",SEARCH_ADDRESS);
        model.addAttribute("ssoAddress",SSO_ADDRESS);
        model.addAttribute("registerAddress",SSO_ADDRESS+"/user/register/page");
        model.addAttribute("portalAddress",PORTAL_ADDRESS);
        model.addAttribute("cartAddress",CART_ADDRESS);
        return "index";
    }

}
