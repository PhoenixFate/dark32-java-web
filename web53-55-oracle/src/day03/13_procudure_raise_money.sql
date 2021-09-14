-- 给指定员工涨工资
-- in 表示输入参数
create or replace procedure raise_salary(eno in number)
is
-- 定义变量保存涨前的薪水
    psal emp.sal%type;
begin
    -- 得到涨前的薪水
    select sal into psal from emp where empno=eno;
    -- 涨100
    update emp set sal=sal+100 where empno=eno;
    -- 一般不在存储过程中commit，在调用过程中开控制commit
    dbms_output.put_line('涨前：'||psal || '涨后：'||(psal+100));
end;


-- 调用
begin
    raise_salary(7839);
    raise_salary(7566);
    commit;
end;