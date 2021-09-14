/**
  触发器的作用：实施负责的安全性检查
  禁止在非工作时间 插入新员工
  周末: to_char(sysdate,'day') in ('saturday','sunday')
  上班前，下班后:  to_number(to_char(sysdate,'hh24')) not between 9 and 18
*/

-- 针对表的触发器（针对语句的触发器）
create or replace trigger security_emp_trigger
before insert
on emp
begin
    if to_char(sysdate,'day') in ('saturday','sunday') or
        to_number(to_char(sysdate,'hh24')) not between 9 and 18 then
        -- 禁止insert
        -- 抛出应用层的错误, 错误代码必须在 -20000 - -29999 之间
        raise_application_error(-20008,'不能在非工作日的时间内插入数据');
    end if;

end security_emp_trigger;