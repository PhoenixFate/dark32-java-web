package com.phoenix.item.controller;

import com.phoenix.item.dto.ItemDto;
import com.phoenix.item.pojo.Item;
import com.phoenix.item.pojo.ItemDescription;
import com.phoenix.item.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品详情页面
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${HTML_FREEMARKER_PATH}")
    private String HTML_FREEMARKER_PATH;
    @Value("${CART_ADDRESS}")
    private String CART_ADDRESS;
    @Value("${PORTAL_ADDRESS}")
    private String PORTAL_ADDRESS;
    @Value("${SSO_ADDRESS}")
    private String SSO_ADDRESS;

    @RequestMapping("{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model){
        //调用商品取商品基本信息
        Item item = itemService.getItemById(itemId);
        System.out.println(item);
        //调用商品取商品详细信息
        ItemDescription itemDescription = itemService.getItemDescriptionById(itemId);
        ItemDto itemDto=new ItemDto();
        itemDto.setItem(item);
        itemDto.setItemDescription(itemDescription);
        if(item.getImage()!=null && !"".equals(item.getImage())){
            itemDto.setImages(item.getImage().split(","));
        }
        model.addAttribute("itemDto",itemDto);
        model.addAttribute("cartAddress",CART_ADDRESS);
        model.addAttribute("portalAddress",PORTAL_ADDRESS);
        model.addAttribute("ssoAddress",SSO_ADDRESS);
        model.addAttribute("registerAddress",SSO_ADDRESS+"/user/register/page");

        return "item";
    }

    @RequestMapping("freemarker/{itemId}")
    public String showItemInfoStatic(@PathVariable Long itemId) throws IOException, TemplateException {
        //判断该静态页面是否存在
        File file = new File(HTML_FREEMARKER_PATH + "/item_" + itemId + ".html");
        if(file.exists()){
            System.out.println("文件已经存在");
        }else {
            System.out.println("文件不存在");
            Item item = itemService.getItemById(itemId);
            ItemDto itemDto=new ItemDto();
            itemDto.setItem(item);
            itemDto.setImages(item.getImage().split(","));
            ItemDescription itemDescription = itemService.getItemDescriptionById(itemId);
            itemDto.setItemDescription(itemDescription);

            //创建数据集，把商品信息封装进去
            Map<String,Object> map=new HashMap<>();
            map.put("itemDto",itemDto);

            //加载模板对象
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            //加载模板
            Template template = configuration.getTemplate("item.ftl");

            //创建一个输出流，指定输出的目录及文件名
            //指定文件输出路径及文件名
            Writer writer=new FileWriter(new File(HTML_FREEMARKER_PATH+"/item_"+itemId+".html"));
            //生成静态页面
            template.process(map,writer);
            //关闭流
            writer.close();
        }
        return "redirect:/temp/item_"+itemId+".html";
    }

}
