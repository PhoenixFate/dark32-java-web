package com.phoenix.jdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

public class JdbcTemplateDemo {

    @Test
    public void test01() throws PropertyVetoException {
        //0.准备连接池
        ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://114.67.89.253:3306/darkhorse32_hibernate");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("centos123qwer");
        //1.创建jdbcTemplate对象
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(comboPooledDataSource);
        //2.书写sql
        String sql="insert into tb_user values(null,'tom')";
        int count = jdbcTemplate.update(sql);
        System.out.println(count);
    }


}
