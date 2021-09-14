-- 包头
-- 查询某个部门中的所有员工信息 -> 返回的是集合
create or replace package emp_list_package
is
    type empcursor is ref cursor;
    procedure query_emp_list(dno in number,empList out empcursor);
end emp_list_package;



-- 包体
create or replace package body emp_list_package
is
    procedure query_emp_list(dno in number,emplist out empcursor)
    as
    begin
        open empList for select * from emp where deptno=dno;

    end;
end emp_list_package;