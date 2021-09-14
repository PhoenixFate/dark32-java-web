-- 自定义异常
declare
    cursor cemp is select ename from emp where deptno=50;
    pname emp.ename%type;
    -- 自定义异常
    no_emp_found exception;
begin
    open cemp;
    -- 取第一条记录
    fetch cemp into pname;
    if cemp%notfound then
        -- 抛出异常
        raise no_emp_found;
    end if;
    -- 进程：pmon进程（process monitor进程会自动 清楚数据库缓存区高速缓存、释放该用户进程使用的资源）
    close cemp;
exception
    when no_emp_found then dbms_output.put_line('没有找到员工');
    when others then dbms_output.put_line('其他异常');
end;