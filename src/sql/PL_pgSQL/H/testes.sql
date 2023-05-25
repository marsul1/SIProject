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
	DELETE FROM player_badge where badge_name = 'test';
	raise notice 'Teste Ex H';
	begin
		call associar_cracha(2, 'G3', 'test');
		IF EXISTS(SELECT * FROM player_badge WHERE game_ref = 'G3' AND badge_name ='test' AND player_id = 2) then
			raise notice 'teste 1: Cracha adicionado bons dados: Resultado OK';
		ELSE
			raise notice 'teste 1: Cracha adicionado bons dados: Resultado FAIL';
			exit l;
		END IF;
		begin
			call associar_cracha(1, 'G4', 'fight');
			raise notice 'teste 2: Cracha adicionado sem pontos que chegue: Resultado FAIL';
			exit l;
			exception
			   when others then
				 raise notice 'teste 2: Cracha adicionado sem pontos que chegue: Resultado OK';
		end;
		begin
			call associar_cracha(2, 'G3', 'test');
			raise notice 'teste 3: Cracha adicionado repetido: Resultado FAIL';
			exit l;
			exception
			   when others then
				 raise notice 'teste 3: Cracha adicionado repetido: Resultado OK';
		end;
	end;
	rollback;
end;
$$ ;