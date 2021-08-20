rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
--引用型变量: 查询并打印7839的姓名和薪水

declare
  --定义变量保存姓名和薪水
  --pename varchar2(20);
  --psal   number;
  pename emp.ename%type;
  psal   emp.sal%type;
begin
  --得到7839的姓名和薪水
  select ename,sal into pename,psal from emp where empno=7839;

  --打印
  dbms_output.put_line(pename||'的薪水是'||psal);
end;
/
