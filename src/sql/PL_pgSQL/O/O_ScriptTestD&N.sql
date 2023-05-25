
--Testes Exercicio D
--teste 1: verificar se a regra de negócio R1 da lógica está a funcionar corretamente, usando o nível 3
do language plpgsql
$$
<<l>>
DECLARE
code char(5) DEFAULT '00000';
    msg text;
begin
rollback;
--set transaction isolation level repeatable read;
DELETE FROM players where id=1000 OR username = 'testaa';
raise notice 'Teste Ex D';
begin
INSERT INTO players VALUES (1000, 'test@test.com', 'password123', 'Ativo','Europe');
begin
CALL create_player('test2@gmail.com', 'testaa', 'Europe');
IF EXISTS(SELECT state FROM players WHERE email = 'test2@gmail.com' AND username ='testaa' AND region_name = 'Europe') then
			raise notice 'teste 1: Create jogador com dados bem passados: Resultado OK';
ELSE
			raise notice 'teste 1: Create jogador com dados bem passados: Resultado FAIL';
			exit l;
END IF;
exception
		   when others then
		      GET stacked DIAGNOSTICS
                          code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
		      raise notice 'code=%, msg=%',code,msg;
		     raise notice 'teste 1: Create jogador com dados bem passados: Resultado FAIL';
end;
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

--Testes Exercicio E
do language plpgsql
$$
<<l>>
declare
points integer;
begin
	raise notice 'Teste Ex E';
rollback;
set transaction isolation level repeatable read;
select total_pontos_jogador(3) into points;
if(points = 1800) then
		raise notice 'teste 1: Ver pontos de jogador que existe: Resultado OK';
else
		raise notice 'teste 1: Ver pontos de jogador que existe: Resultado FAIL';
end if;
begin
select total_pontos_jogador(10000) into points;
raise notice 'teste 2: Ver pontos de jogador que não existe: Resultado FAIL';
exception
		   when others then
		     raise notice 'teste 2: Ver pontos de jogador que não existe: Resultado OK';
end;
rollback;
end;
$$ ;

--Testes Exercicio H
do language plpgsql
$$
<<l>>
declare
jogos integer;
begin
	raise notice 'Teste Ex F';
rollback;
set transaction isolation level repeatable read;
select total_jogos_jogador(3) into jogos;
if(jogos = 2) then
		raise notice 'teste 1: Ver jogos de jogador que existe: Resultado OK';
else
		raise notice 'teste 1: Ver jogos de jogador que existe: Resultado FAIL';
end if;
begin
select total_jogos_jogador(5432);
raise notice 'teste 2: Ver jogos de jogador que não existe: Resultado FAIL';
exception
		   when others then
		     raise notice 'teste 2: Ver jogos de jogador que não existe: Resultado OK';
end;
rollback;
end;
$$ ;

--Testes Exercicio I
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

--Testes Exercicio J
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

--Testes Exercicio K
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

--Testes Exercicio L
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
raise notice 'Teste Ex L';
begin
		IF NOT EXISTS(select * from jogador_total_info where id = 7) then
			raise notice 'teste 1: jogador banido tem info: Resultado OK';
ELSE
			raise notice 'teste 1: jogador banido tem info: Resultado FAIL';
			exit l;
END IF;
end;
rollback;
end;
$$ ;

--Testes Exercicio M


--Testes Exercicio N
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
raise notice 'Teste Ex N';
begin
DELETE from jogador_total_info where id = 5 ;
IF NOT EXISTS(select * from jogador_total_info where id = 5) then
			raise notice 'teste 1: jogador banido tem info: Resultado OK';
ELSE
			raise notice 'teste 1: jogador banido tem info: Resultado FAIL';
			exit l;
END IF;
end;
rollback;
end;
$$ ;

