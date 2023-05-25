create or replace procedure iniciar_conversa_logica(
    IN player_id_param integer,
    IN conversation_name varchar(255),
	OUT conversation_id integer
)
as
$$
declare
    current_id integer;
begin
	IF NOT EXISTS(SELECT * FROM players WHERE id = player_id_param and state='Ativo') THEN
        RAISE EXCEPTION 'Impossivel criar conversa com jogador não ativo';
    END IF;
	
    INSERT INTO conversations (name) VALUES (conversation_name);
	
    SELECT id FROM conversations ORDER BY id DESC LIMIT 1 INTO current_id;
    
    insert into players_conversation (player_id, conversations_id)
    values (player_id_param,current_id);
	conversation_id := current_id;

end;
$$ language plpgsql;


CREATE OR REPLACE PROCEDURE iniciar_conversa(
    IN player_id_param integer,
    IN conversation_name varchar(255),
	OUT conversation_id integer
)
AS
$$
DECLARE
    code char(5) DEFAULT '00000';
    msg text;
BEGIN
	rollback;
	set transaction isolation level read committed; --should be --repeatable read
    BEGIN
		CALL iniciar_conversa_logica(player_id_param, conversation_name, conversation_id);
		EXCEPTION
			WHEN OTHERS THEN
				GET STACKED DIAGNOSTICS
					code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
				RAISE NOTICE 'code=%, msg=%', code, msg;
				ROLLBACK;
    END;
END;
$$ LANGUAGE plpgsql;




