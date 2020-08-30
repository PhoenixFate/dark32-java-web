package com.phoenix.transfer.service;

import com.phoenix.transfer.dao.TransferDao;
import com.phoenix.utils.MyDataSourceUtils;

import java.sql.SQLException;

public class TransferService {

    public boolean transfer(String out, String in, double money) {
        TransferDao transferDao=new TransferDao();
        boolean isTransferSuccess=true;
        //Connection connection=null;
        try {
            //connection= DataSourceUtils.getConnection();
            //connection.setAutoCommit(false);

            //使用ThreadLocal存储Connection（不建议）
            //MyDataSourceUtils.getCurrentConnection();

            //直接开启事务，隐藏connection
            MyDataSourceUtils.startTransaction();

            //转出钱的方法
            transferDao.out(out,money);
            //int i=1/0;
            //转入钱的方法
            transferDao.in(in, money);
        } catch (Exception e) {
            //事务回滚
//            try {
//                connection.rollback();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
            e.printStackTrace();
            isTransferSuccess=false;
        }finally {
            //最后提交事务
//            try {
//                connection.commit();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            try {
                MyDataSourceUtils.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return isTransferSuccess;
    }
}
