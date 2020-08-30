package com.phoenix.dao.impl;

import com.phoenix.bean.User;
import com.phoenix.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 使用spring模板实现增删改查
 */
public class UserDaoImpl  implements UserDao {
    // public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public Integer save(User user) {
        // super.getJdbcTemplate();


        String sql="insert into tb_user values(null,?)";
        int count = jdbcTemplate.update(sql, user.getName());
        return count;
    }

    public Integer update(User user) {
        String sql="update tb_user set name=? where id=?";
        int count = jdbcTemplate.update(sql, user.getName(), user.getId());
        return count;
    }

    public Integer delete(Integer id) {
        String sql="delete from tb_user where id=?";
        int count = jdbcTemplate.update(sql,id);
        return count;
    }

    public User getById(Integer id) {
        String sql="select * from tb_user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                return user;
            }
        }, id);
        return user;
    }

    public Integer getTotalCount() {
        String sql="select count(*) from tb_user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    public List<User> getAll() {
        String sql="select * from tb_user";
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

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
