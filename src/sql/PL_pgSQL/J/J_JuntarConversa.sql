--Criar o procedimento armazenado juntarConversa que recebe como parâmetros os
--identificadores de um jogador e de uma conversa e junta esse jogador a essa conversa.

create or replace procedure  juntar_conversa_logica(
    player_id_param integer,
    conversation_id_param integer
)
as
$$
declare
    check_player_conversation integer;
begin
    select player_id from players_conversation
                     where player_id = player_id_param
                       and conversations_id = conversation_id_param into check_player_conversation ;
    if (check_player_conversation is not null) then
        RAISE EXCEPTION 'Player already in the conversation';
	elseif (not exists(select * from players where id = player_id_param AND state !='Activo')) then
		RAISE EXCEPTION 'Cant add player to conversation';
    end if;
	
    insert into players_conversation (player_id, conversations_id)
    values (player_id_param,conversation_id_param);
	
end;
$$ language plpgsql;

CREATE OR REPLACE PROCEDURE juntar_conversa(
    player_id_param integer,
    conversation_id_param integer
)
AS
$$
DECLARE
    code char(5) DEFAULT '00000';
    msg text;
BEGIN
	rollback;
	set transaction isolation level read committed;
    BEGIN
		CALL juntar_conversa_logica(player_id_param, conversation_id_param);
		EXCEPTION
			WHEN OTHERS THEN
				GET STACKED DIAGNOSTICS
					code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
				RAISE NOTICE 'code=%, msg=%', code, msg;
				ROLLBACK;
    END;
END;
$$ LANGUAGE plpgsql;

