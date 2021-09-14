-- 统计每年入职的人数
declare
    cursor cemp is select to_char(hiredate,'yyyy') from emp;
    hire_year varchar(4);
    -- 每年入职人数量
    count80 number:=0;
    count81 number:=0;
    count82 number:=0;
    count87 number:=0;
begin
    open cemp;
    loop
        -- 取一个员工的入职年份
        fetch cemp into hire_year;
        exit when cemp%notfound;

        -- 判断年份是那一年
        if hire_year = '1980' then count80:=count80+1;
        elsif hire_year='1981' then count81:=count81+1;
        elsif hire_year='1982' then count82:=count82+1;
        else count87:=count87+1;
        end if;
    end loop;
    close cemp;
    -- 输出数据
    dbms_output.put_line('total:' || (count80+count81+count82+count87));
    dbms_output.put_line('1980:'||count80);
    dbms_output.put_line('1981:'||count81);
    dbms_output.put_line('1982:'||count82);
    dbms_output.put_line('1987:'||count87);
end;