declare
    u varchar2(64);
begin
	-- Converting current user name FLOWFX_USER or FLOWFX_USER_01 to FLOWFX or FLOWFX_01
	select REPLACE(user, '_USER', '') into u from dual;

	dbms_output.put_line('');
	dbms_output.put_line('Creating synonyms in schema ' || user || ', using ' || u || ' as source schema');

	declare
		cursor ix is
			select * from all_objects where owner=u and object_type in ('TABLE', 'SEQUENCE');
	begin
		for x in ix loop
			dbms_output.put_line('	Creating synonym for ' || x.object_type || ' ' || x.object_name);
			execute immediate('create or replace synonym ' || x.object_name || ' for ' || u || '.' || x.object_name);
		end loop;
	end;
end;