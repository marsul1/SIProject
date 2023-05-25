--Criar o procedimento armazenado enviarMensagem que recebe como parâmetros os
--identificadores de um jogador e de uma conversa e o texto de uma mensagem e procede ao
--envio dessa mensagem para a conversa indicada, associando-a ao jogador também indicado.

create or replace procedure enviar_mensagem_logica(
    player_id_param integer,
    conversation_id_param integer,
    message_text_ text
)
as
$$
declare
    current_message_number integer;
    current_timestamp_ timestamp;
begin
	IF NOT EXISTS(SELECT * FROM players WHERE id = player_id_param and state='Ativo') THEN
        RAISE EXCEPTION 'Jogador bloqueado de enviar mensagens';
	ELSEIF (NOT EXISTS(SELECT * FROM players_conversation WHERE player_id = player_id_param and conversations_id=conversation_id_param)) THEN
		RAISE EXCEPTION 'Jogador não se encontra na conversa';
    END IF;
	
    select COALESCE(max(message_number),0) --se for null vai dar que a ten nasse_number = 1
    from messages
    where conversation_id = conversation_id_param
        into current_message_number ;
		
    current_timestamp_ := now();
	
    insert into messages (message_number,sent_time,text,conversation_id,player_id)
    values (current_message_number+1,current_timestamp_,message_text_,conversation_id_param,player_id_param);
end;
$$ language plpgsql;


CREATE OR REPLACE PROCEDURE enviar_mensagem(
    player_id_param integer,
    conversation_id_param integer,
    message_text_ text
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
		CALL enviar_mensagem_logica(player_id_param, conversation_id_param,message_text_);
		EXCEPTION
			WHEN OTHERS THEN
				GET STACKED DIAGNOSTICS
					code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
				RAISE NOTICE 'code=%, msg=%', code, msg;
				ROLLBACK;
    END;
END;
$$ LANGUAGE plpgsql;



