rem PL/SQL Developer Test Script

set feedback off
set autoprint off

rem Execute PL/SQL Block
--打印Hello World

declare
  --说明部分
begin
  --程序
  dbms_output.put_line('Hello World');
end;
/
