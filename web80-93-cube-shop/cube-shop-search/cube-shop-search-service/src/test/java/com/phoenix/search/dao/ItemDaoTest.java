package com.phoenix.search.dao;

import com.phoenix.common.pojo.SearchItem;
import com.phoenix.search.mapper.SearchItemMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class ItemDaoTest {

    @Test
    public void test01(){
        //初始化spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        //从容器中获得JedisClient对象
        SearchItemMapper searchItemMapper=applicationContext.getBean(SearchItemMapper.class);
        List<SearchItem> itemList = searchItemMapper.getItemList();

        for(SearchItem searchItem:itemList){
            System.out.println(searchItem);
        }

    }


}
