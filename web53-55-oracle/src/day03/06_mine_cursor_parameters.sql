declare
    -- 形参
    cursor cemp(deptNo number) is select ename,sal from emp where deptno=deptNo;
    pname emp.ename%type;
    psal emp.sal%type;
begin
    -- 实参
    open cemp(10);
    loop
        fetch cemp into pname,psal;
        exit when cemp%notfound;
        dbms_output.put_line(pname||'的薪水是: '||psal);
    end loop;
    close cemp;
end;