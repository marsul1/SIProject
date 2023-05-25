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
	DELETE FROM players_conversation where player_id = 2 and conversations_id = 3;
	raise notice 'Teste Ex J';
	begin
		call juntar_conversa(2,3);
		IF EXISTS(SELECT * FROM players_conversation where player_id = 2 and conversations_id = 3) then
			raise notice 'teste 1: Jogador adicionado à conversa com sucesso: Resultado OK';
		ELSE
			raise notice 'teste 1: Jogador adicionado à conversa com sucesso: Resultado FAIL';
			exit l;
		END IF;
		begin
			call juntar_conversa(2,3);
			raise notice 'teste 2: Jogador adicionado à conversa repetido: Resultado FAIL';
			exit l;
			exception
			   when others then
				 raise notice 'teste 2: Jogador adicionado à conversa repetido: Resultado OK';
		end;
		begin
			call juntar_conversa(7,3);
			raise notice 'teste 3: Jogador banido adicionado à conversa: Resultado FAIL';
			exit l;
			exception
			   when others then
				 raise notice 'teste 3: Jogador banido adicionado à conversa: Resultado OK';
		end;
	end;
	rollback;
end;
$$ ;