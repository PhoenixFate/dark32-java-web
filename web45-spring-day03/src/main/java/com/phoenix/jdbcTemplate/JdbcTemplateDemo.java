package com.phoenix.jdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.phoenix.bean.User;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateDemo {

    @Test
    public void test01() throws PropertyVetoException {
        //0.准备连接池
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://www.bytes-space.com:63306/darkhorse32_hibernate");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("centos123qwer");
        //1.创建jdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(comboPooledDataSource);
        //2.书写sql
        String sql = "insert into tb_user values(null,'tom')";
        int count = jdbcTemplate.update(sql);
        System.out.println(count);
    }

    @Test
    public void test02() throws PropertyVetoException {
        //0.准备连接池
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://www.bytes-space.com:63306/darkhorse32_hibernate");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("centos123qwer");
        //1.创建jdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(comboPooledDataSource);
        //2.书写sql
        String sql = "select * from tb_user";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                return user;
            }
        });
        System.out.println(userList);
    }

}
