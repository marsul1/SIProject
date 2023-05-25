--teste 1: verificar se a regra de negócio R1 da lógica está a funcionar corretamente, usando o nível 3
do language plpgsql
$$
<<l>>
DECLARE
    code char(5) DEFAULT '00000';
    msg text;
begin 
	rollback;
	set transaction isolation level repeatable read;
	DELETE FROM messages where player_id = 2 and conversation_id = 1 and text = 'We can do it' ;
	raise notice 'Teste Ex K';
	begin
		call enviar_mensagem(2,1,'We can do it');
		IF EXISTS(SELECT * FROM messages where player_id = 2 and conversation_id = 1 and text = 'We can do it') then
			raise notice 'teste 1: mensagem enviada com sucesso: Resultado OK';
		ELSE
			raise notice 'teste 1: mensagem enviada com sucesso: Resultado FAIL';
			exit l;
		END IF;
		begin
			call juntar_conversa(5,3);
			raise notice 'teste 3: Jogador não está conversa falha envio: Resultado FAIL';
			exit l;
			exception
			   when others then
				 raise notice 'teste 3: Jogador não está conversa falha envio: Resultado OK';
		end;
	end;
	rollback;
end;
$$ ;