--查询某个员工的年收入

create or replace function queryEmpIncome(eno in number) 
return number
is
       --定义变量保存月薪和奖金
       psal emp.sal%type;
       pcomm emp.comm%type;
begin
       --得到月薪和奖金
       select sal,comm into psal,pcomm from emp where empno=eno; 
       
       --返回年收入
       return psal*12+nvl(pcomm,0);

end queryEmpIncome;
/
