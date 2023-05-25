CREATE OR REPLACE PROCEDURE ban_player_logic(uid integer)
AS
$$
BEGIN
    CALL changePlayerStatus_logic(uid,'Banido');
END;
$$ LANGUAGE plpgsql;

--Should only be called inside another transaction block
CREATE OR REPLACE PROCEDURE changePlayerStatus_logic(uid integer, status varchar(20))
AS
$$
BEGIN
    update players
    set state = status
    where id = uid;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE ban_player(uid integer)
AS $$
DECLARE
    code char(5) DEFAULT '00000';
    msg text;
BEGIN
	rollback;
	set transaction isolation level read committed;
    BEGIN
	CALL ban_player_logic(uid);
	EXCEPTION
		WHEN OTHERS THEN
			GET STACKED DIAGNOSTICS
				code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
			RAISE NOTICE 'code=%, msg=%', code, msg;
			ROLLBACK;
	END;
END;
$$ LANGUAGE plpgsql;
