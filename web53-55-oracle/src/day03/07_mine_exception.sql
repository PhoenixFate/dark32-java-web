-- 系统已经定义好的异常
-- no_data_found  没有找到数据
-- too_many_rows  select...into语句匹配多行
-- zero_divide 被零除
-- value_error 算术或转换错误
-- timeout_on_resource 在等待资源时发生超时

declare
    pnum number;
begin
    pnum:=1/0;
exception
    when zero_divide then dbms_output.put_line('0不能做分母1');
                          dbms_output.put_line('0不能做分母2');
    when value_error then dbms_output.put_line('算术或者转换错误');
    when others then dbms_output.put_line('其他异常');
end;

