do language plpgsql
$$ 
<<l>>
declare
	pontos integer;
begin 
	raise notice 'Teste Ex G';
	rollback;
	set transaction isolation level repeatable read;
	if((select total_points  from pontos_jogo_por_jogador('G3') LIMIT 1) = 1500) then
		raise notice 'teste 1: Pontos do jogo G3 correto: Resultado OK';
	else
		raise notice 'teste 1: Pontos do jogo G3 correto: Resultado FAIL';
	end if;
	select sum(total_points)from pontos_jogo_por_jogador('G4') into pontos;
	if(pontos = 1000) then
		raise notice 'teste 2: Ignora jogos que nao acabaram: Resultado OK';
	else
		raise notice 'teste 2: Ignora jogos que nao acabaram: Resultado FAIL';
	end if;
	rollback;
end;
$$ ;