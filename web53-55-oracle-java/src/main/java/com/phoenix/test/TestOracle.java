package com.phoenix.test;

import com.phoenix.common.JDBCUtils;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TestOracle {


    /**
     * java执行存储过程
     * <p>
     * -- 存储过程、存储函数都可以通过out指定一个或者多个输出参数
     * -- 原则：如何只有一个返回值，用存储函数，否则用存储过程
     * <p>
     * create or replace procedure query_emp_info(eno in number,pname out varchar,psal out number,pjob out varchar)
     * is
     * begin
     * select ename,sal,job into pname,psal,pjob from emp where empno=eno;
     * <p>
     * end query_emp_info;
     */
    @Test
    public void testProcedure() {
        String sql = "{call query_emp_info(?,?,?,?)}";
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            callableStatement = connection.prepareCall(sql);
            //对于in 参数，需要赋值
            callableStatement.setInt(1, 7839);
            //对于out参数，需要声明
            callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
            callableStatement.registerOutParameter(3, OracleTypes.NUMBER);
            callableStatement.registerOutParameter(4, OracleTypes.VARCHAR);
            //执行调用
            callableStatement.execute();
            //输出
            String name = callableStatement.getString(2);
            Double salary = callableStatement.getDouble(3);
            String job = callableStatement.getString(4);
            System.out.println("name: " + name + "; salary: " + salary + "; job: " + job);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, callableStatement, null);
        }

    }


    /**
     * java执行存储函数
     * <p>
     * -- 查询某个员工的年收入
     * create or replace function query_annual_income(eno in number)
     * return number
     * is
     * -- 定义变量保存月薪和奖金
     * psal emp.sal%type;
     * pcomm emp.comm%type;
     * begin
     * -- 得到月薪和奖金
     * select sal,comm into psal,pcomm from emp where empno=eno;
     * <p>
     * -- 返回年收入
     * return psal*12+nvl(pcomm,0);
     * <p>
     * end query_annual_income;
     */
    @Test
    public void testFunction() {
        String sql = "{?=call query_annual_income(?)}";
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            callableStatement = connection.prepareCall(sql);
            //对于存储函数，一个参数为输出参数
            //对于out参数，需要声明
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            //对于in 参数，需要赋值
            callableStatement.setInt(2, 7839);
            //执行调用
            callableStatement.execute();
            //输出
            Double salary = callableStatement.getDouble(1);
            System.out.println("annual salary: " + salary);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, callableStatement, null);
        }
    }

    /**
     * java执行oracle中的包
     */
    @Test
    public void testPackage() {
        String sql = "{call emp_list_package.query_emp_list(?,?)}";
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet=null;
        try {
            connection = JDBCUtils.getConnection();
            callableStatement = connection.prepareCall(sql);
            //对于in 参数，需要赋值
            callableStatement.setInt(1, 10);
            //对于out参数，需要声明
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            //执行调用
            callableStatement.execute();
            //取出结果（结果是个集合）
            resultSet = ((OracleCallableStatement) callableStatement).getCursor(2);
            while (resultSet.next()) {
                String name = resultSet.getString("ename");
                String job = resultSet.getString("job");
                Double salary = resultSet.getDouble("sal");
                System.out.println("name: " + name + "; salary: " + salary + "; job: " + job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(connection, callableStatement, resultSet);
        }
    }

}
