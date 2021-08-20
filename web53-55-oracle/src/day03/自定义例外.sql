rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
-- 查询50号部门的员工
declare 
  cursor cemp  is select ename from emp where deptno=50;
  pename emp.ename%type;
  
  --自定义例外
  no_emp_found exception;
begin
  open cemp;
  
  --取第一条记录
  fetch cemp into pename;
  if cemp%notfound then
    --抛出例外
    raise no_emp_found;
  end if;
  
  --进程：pmon进程(proccesss monitor)
  close cemp;

exception
  when no_emp_found then dbms_output.put_line('没有找到员工');
  when others then dbms_output.put_line('其他例外');
end;
/
