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
		DELETE from jogadorTotalInfo where id = 5 ;
		IF NOT EXISTS(select * from jogadorTotalInfo where id = 5) then
			raise notice 'teste 1: jogador banido tem info: Resultado OK';
		ELSE
			raise notice 'teste 1: jogador banido tem info: Resultado FAIL';
			exit l;
		END IF;
	end;
	rollback;
end;
$$ ;