
create trigger after_insert_emp_trigger
after insert
on emp
declare
begin
    dbms_output.put_line('成功插入员工表');
end;
