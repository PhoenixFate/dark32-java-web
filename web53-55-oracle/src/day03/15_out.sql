-- 存储过程、存储函数都可以通过out指定一个或者多个输出参数
-- 原则：如何只有一个返回值，用存储函数，否则用存储过程

create or replace procedure query_emp_info(eno in number,pname out varchar,psal out number,pjob out varchar)
is
begin
    select ename,sal,job into pname,psal,pjob from emp where empno=eno;

end query_emp_info;


