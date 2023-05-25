do language plpgsql
$$ 
<<l>>
declare
	jogos integer;
	conversation_id integer;
begin 
	raise notice 'Teste Ex I';
	rollback;
	set transaction isolation level repeatable read;
	call iniciar_conversa(1,'marsul lasod ne terren',conversation_id);
	if(conversation_id = (select max(id) from conversations)) then
		raise notice 'teste 1: Conversa criada com sucesso: Resultado OK';
	else
		raise notice 'teste 1: Conversa criada com sucesso: Resultado FAIL';
	end if;
	if(exists(select * from players_conversation as pc where pc.player_id = 1 AND pc.conversations_id = conversation_id)) then
	raise notice 'teste 2: Player adicionado com sucesso: Resultado OK';
	else
		raise notice 'teste 2: Player adicionado com sucesso: Resultado FAIL';
	end if;
	begin
		call iniciar_conversa(7,'conversa 2',conversation_id);
		raise notice 'teste 3: Conversar com jogador banido: Resultado FAIL';
			exit l;
			exception
			   when others then
				 raise notice 'teste 3: Conversar com jogador banido: Resultado OK';
	end;
	rollback;
end;
$$ ;