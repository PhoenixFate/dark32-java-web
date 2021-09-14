declare
    -- 定义变量
    pname varchar(20);
    psal number;
    -- :=用于给变量赋值
    married boolean:=true;
    -- 引用员工表的姓名的类型作为当前变量的类型
    pname2 emp.ename%type;
    psal2 emp.sal%type;
    -- 定义记录型变量：代表一行
    emp_rec emp%rowtype;
begin
    -- 通过into来给变量赋值，into后面的顺序需要跟into前面的变量顺序一致
    select ename,sal into pname,psal from emp where empno=7839;
    dbms_output.put_line(pname || '的薪水是：' || psal);
    select ename,sal into pname2,psal2 from emp where empno=7698;
    dbms_output.put_line(pname2 || '的薪水是: ' || psal2);
    select * into emp_rec from emp where empno=7839;
    dbms_output.put_line(emp_rec.ename || '的薪水是' || emp_rec.sal);
end;