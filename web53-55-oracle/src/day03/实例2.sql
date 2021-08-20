rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
/*
1、SQL语句
selet empno,sal from emp order by sal;
---> 光标  ---> 循环  ---> 退出：1. 总额>5w   2. notfound

2、变量：（*）初始值  （*）最终如何得到
涨工资的人数: countEmp number := 0;
涨后的工资总额：salTotal number;
(1)select sum(sal) into salTotal from emp;
(2)涨后=涨前 + sal *0.1

练习： 人数：8    总额:50205.325
*/
declare
    cursor cemp is select empno,sal from emp order by sal;
    pempno emp.empno%type;
    psal   emp.sal%type;
    
    --涨工资的人数: 
    countEmp number := 0;
    --涨后的工资总额：
    salTotal number;
begin
    --得到工资总额的初始值
    select sum(sal) into salTotal from emp;
    
    open cemp;
    loop
         -- 1. 总额 >5w
         exit when salTotal > 50000;
         --取一个员工
         fetch cemp into pempno,psal;
         --2. notfound
         exit when cemp%notfound;
         
         --涨工资
         update emp set sal=sal*1.1 where empno=pempno;
         --人数+1
         countEmp := countEmp +1;
         --涨后=涨前 + sal *0.1
         salTotal := salTotal + psal * 0.1;

    end loop;
    close cemp;
    
    commit;
    dbms_output.put_line('人数：'||countEmp||'    总额:'||salTotal);
end;
/
