-- 存储过程（存储函数）：存储在数据库中供所有用户调用的子程序
-- 存储过程没有return返回值；存储函数必须有return返回值

create or replace procedure say_hello
-- is/as 都行
as
    -- 说明部分
begin
    dbms_output.put_line('hello world');
end;

/**
-- 调用存储过程
-- 1. exec say_hello()
-- 2. begin
        say_hello
      end;
ps: say_hello()/say_hello; 都能调用存储过程

 */
