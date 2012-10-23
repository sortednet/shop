declare
	cursor objects is
		select * from all_objects
		where owner=user and object_type in ('TABLE','VIEW','FUNCTION','SEQUENCE', 'PACKAGE', 'TRIGGER', 'PROCEDURE', 'SYNONYM', 'PACKAGE BODY', 'DATABASE LINK');
begin
	dbms_output.put_line('');
	dbms_output.put_line('Dropping all objects in schema ' || user);

	for o in objects loop
		begin
			dbms_output.put_line('	Dropping ' || o.object_type || ' ' || o.object_name);
			if o.object_type = 'TABLE' then
				execute immediate('drop ' || o.object_type || ' ' || o.object_name || ' cascade constraints');
			else
				execute immediate('drop ' || o.object_type || ' ' || o.object_name );
			end if;
		end;
	end loop;
	execute immediate('purge recyclebin');
end;