package com.phoenix.pageHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phoenix.item.mapper.ItemMapper;
import com.phoenix.item.pojo.Item;
import com.phoenix.item.pojo.ItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PageHelperTest {

    @Test
    public void testPageHelper(){
        //初始化Spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        //从容器中获取Mapper代理对象
        ItemMapper itemMapper=applicationContext.getBean(ItemMapper.class);

        //执行sql语句之前设置分页信息
        PageHelper.startPage(1,10);

        //执行查询（自动添加分页信息，只对设置分页信息之后的下一句sql添加分页信息，如果有两句sql，需要分别设置两次分页信息）
        ItemExample itemExample=new ItemExample();
        List<Item> itemList = itemMapper.selectByExample(itemExample);

        //取分页信息
        PageInfo<Item> pageInfo=new PageInfo<>(itemList);

        //pageInfo中可以获得total、pages（总页数）等相关信息
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        for(Item item:pageInfo.getList()){
            System.out.println(item);
        }
    }



}
