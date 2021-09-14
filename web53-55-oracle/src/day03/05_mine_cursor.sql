-- 查询和打印所有员工的姓名和薪水
/**
  光标的属性 ：
    %isopen %rowcount(影响的行数)
    %found %notfound
 */
declare
    -- 定义光标（游标）
    cursor cemp is select ename,sal from emp;
    pname emp.ename%type;
    psal emp.sal%type;
begin
    -- 打开光标
    open cemp;
    loop

        -- 读取当前记录
        fetch cemp into pname,psal;
        -- 没有记录的时候退出循环
        exit when cemp%notfound;
        dbms_output.put_line(pname || '的薪水是： '||psal);
    end loop;
    -- 关闭光标
    close cemp;
end;