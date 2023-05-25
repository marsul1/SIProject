CREATE OR REPLACE PROCEDURE deactivate_player_logic(uid integer)
AS
$$
BEGIN
    CALL changePlayerStatus_logic(uid,'Inativo');
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE deactivate_player(uid integer)
AS $$
DECLARE
    code char(5) DEFAULT '00000';
    msg text;
BEGIN
	rollback;
	set transaction isolation level read committed;
    BEGIN
	CALL deactivate_player_logic(uid);
	EXCEPTION
		WHEN OTHERS THEN
			GET STACKED DIAGNOSTICS
				code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
			RAISE NOTICE 'code=%, msg=%', code, msg;
			ROLLBACK;
	END;
END;
$$ LANGUAGE plpgsql;
