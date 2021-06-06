package com.phoenix.dao.impl;

import com.phoenix.bean.User;
import com.phoenix.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 使用spring模板实现增删改查
 * 使用jdbcTemplate的方式二：继承jdbcDaoSupport
 * 但需要连接池
 */
public class UserDaoImpl2 extends JdbcDaoSupport implements UserDao {

    public Integer save(User user) {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();
        String sql = "insert into tb_user values(null,?)";
        int count = jdbcTemplate.update(sql, user.getName());
        return count;
    }

    public Integer update(User user) {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();
        String sql = "update tb_user set name=? where id=?";
        int count = jdbcTemplate.update(sql, user.getName(), user.getId());
        return count;
    }

    public Integer delete(Integer id) {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();
        String sql = "delete from tb_user where id=?";
        int count = jdbcTemplate.update(sql, id);
        return count;
    }

    public User getById(Integer id) {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();
        String sql = "select * from tb_user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                return user;
            }
        }, id);
        return user;
    }

    public Integer getTotalCount() {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();
        String sql = "select count(*) from tb_user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    public List<User> getAll() {
        JdbcTemplate jdbcTemplate = super.getJdbcTemplate();
        String sql = "select * from tb_user";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                return user;
            }
        });
        return userList;
    }

}
