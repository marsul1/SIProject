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