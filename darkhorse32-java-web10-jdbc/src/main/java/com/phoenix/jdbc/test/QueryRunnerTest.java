package com.phoenix.jdbc.test;

import com.phoenix.domain.User;
import com.phoenix.jdbc.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryRunnerTest {


    @Test
    public void test(){
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="insert into tbl_user values(?,?,?,?,?)";
        Object[] params={ 99,"余淮", "耿耿", "余淮", "耿耿"};
        try {
            int rows = queryRunner.update(sql, params);
            System.out.println(rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据id修改用户方法
     *
     */
    @Test
    public void testUpdateUserById() {
        try {
            // 1.创建核心类QueryRunner
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            // 2.编写SQL语句
            String sql = "update tbl_user set upassword=? where uid=?";
            // 3.为站位符设置值
            Object[] params = { "xxx", 99 };
            // 4.执行添加操作
            int rows = qr.update(sql, params);
            if (rows > 0) {
                System.out.println("修改成功!");
            } else {
                System.out.println("修改失败!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAll(){
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from tbl_user";
        try {
            List<User> userList = queryRunner.query(sql, new BeanListHandler<>(User.class));
            for(User user:userList){
                System.out.println(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  getUser(){
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from tbl_user where uid=?";
        Object[] params={99};
        try {
            User user = queryRunner.query(sql, new BeanHandler<>(User.class), params);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCount(){
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select count(*) from  tbl_user";
        try {
            Object query = queryRunner.query(sql, new ScalarHandler());
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  getMapList(){
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from tbl_user";
        try {
            List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler());
            for(Map map:list){
                System.out.println(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getColumn(){
        QueryRunner queryRunner=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from tbl_user";
        try {
            List<Object> list = (List<Object>) queryRunner.query(sql, new ColumnListHandler("name"));
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
