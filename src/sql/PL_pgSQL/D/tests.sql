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
	raise notice 'Teste Ex D';
	DELETE FROM players where username LIKE 'test%';		
	begin
  		INSERT INTO players VALUES (1000, 'test@test.com', 'testaaa', 'Ativo','Europe');
	   	CALL create_player('test2@gmail.com', 'testaa', 'Europe');
		IF EXISTS(SELECT * FROM players WHERE email = 'test2@gmail.com' AND username ='testaa' AND region_name = 'Europe') then
			raise notice 'teste 1: Create jogador com dados bem passados: Resultado OK';
		ELSE
			raise notice 'teste 1: Create jogador com dados bem passados: Resultado FAIL';
			exit l;
		END IF;
	   	begin
			CALL create_player('test2@gmail.com', 'testaa', 'Europe');
			raise notice 'teste 2: Create jogador com repetidos: Resultado FAIL';
			exit l;
			exception
			   when others then
				 raise notice 'teste 2: Create jogador com repetidos fails: Resultado OK';
	   	end;
		CALL deactivate_player(1000);
		IF EXISTS(SELECT state FROM players WHERE id = 1000 AND state ='Inativo') then
			raise notice 'teste 3: Desativar jogador: Resultado OK';
		ELSE
			raise notice 'teste 3: Desativar jogador: Resultado FAIL';
			exit l;
		END IF;
		
		CALL ban_player(1000);
		IF EXISTS(SELECT state FROM players WHERE id = 1000 AND state ='Banido') then
			raise notice 'teste 4: Banir jogador: Resultado OK';
		ELSE
			raise notice 'teste 4: Banir jogador: Resultado FAIL';
			exit l;
		END IF;
	end;
	rollback;
end;
$$ ;
