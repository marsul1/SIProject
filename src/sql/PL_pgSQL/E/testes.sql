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