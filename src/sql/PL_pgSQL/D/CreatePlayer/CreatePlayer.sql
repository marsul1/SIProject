CREATE OR REPLACE PROCEDURE create_player_logic(
    email VARCHAR(255),
    username VARCHAR(255),
    region_name VARCHAR(255)
)
AS
$$
BEGIN
    insert into players(email, username, state, region_name)
    VALUES(email,username,'Ativo',region_name);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE create_player(
    email VARCHAR(255),
    username VARCHAR(255),
    region_name VARCHAR(255)
)
AS $$
DECLARE
    code char(5) DEFAULT '00000';
    msg text;
BEGIN
	rollback;
	set transaction isolation level read committed;
    BEGIN
	CALL create_player_logic(email, username, region_name);
	EXCEPTION
		WHEN OTHERS THEN
			GET STACKED DIAGNOSTICS
				code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
			RAISE NOTICE 'code=%, msg=%', code, msg;
			ROLLBACK;
	END;
END;
$$ LANGUAGE plpgsql;





