/**
  触发器的作用：数据的确认
  涨薪水后的工资不能比 涨之前的工资少
*/
create or replace trigger check_salary_trigger
before update
on emp
-- 行级触发器
for each row
begin
    if :new.sal < :old.sal  then
        raise_application_error(-20010,' 涨薪水后的工资不能比 涨之前的工资少; '||'涨前: '||:old.sal||'; 涨后: '||:new.sal);

    end if;

end check_salary_trigger;

