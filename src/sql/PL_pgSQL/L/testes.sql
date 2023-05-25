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