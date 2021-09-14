-- 判断用户从键盘输入的数字
set serveroutput on
    -- 接受键盘输入
-- 变量num：是一个地址值，在该地址上保存了输入的值
    accept num prompt '请输入一个数字';
declare
    -- 定义变量 保存输入的字 &num取num的值
    pnum number:=&num;
begin
    if pnum = 0 then dbms_output.put_line('您输入的是0');
    elsif pnum > 0 then dbms_output.put_line('您输入的数字大于0');
    else dbms_output.put_line('您输入的数字小于0');
    end if;
end;
/