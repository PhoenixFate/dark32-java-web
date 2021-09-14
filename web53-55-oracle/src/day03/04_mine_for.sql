
declare
    pnum number:=1;
begin
    loop
        exit when pnum>10;
        dbms_output.put_line(pnum);
        pnum:=pnum+1;
    end loop;
end;
