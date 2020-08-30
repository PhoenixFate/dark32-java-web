package com.phoenix.jdbcTemplate;

import com.phoenix.bean.User;
import com.phoenix.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateDemo2 {

    @Resource(name="userDao")
    private UserDao userDao;

    @Test
    public void test01(){
        User user = userDao.getById(1);
        System.out.println(user);

        Integer count = userDao.getTotalCount();
        System.out.println(count);
    }

}
