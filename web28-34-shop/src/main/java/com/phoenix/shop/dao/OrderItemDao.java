package com.phoenix.shop.dao;

import com.phoenix.shop.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderItemDao {

    public List<Map<String, Object>> findByOid(String oid) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select i.itemid, i.count,i.subtotal,p.pid, p.pimage,p.pname,p.shop_price from orderitem i,product p where i.pid=p.pid and i.oid=?";
        List<Map<String, Object>> mapList = queryRunner.query(sql, new MapListHandler(), oid);
        return mapList;
    }


}
