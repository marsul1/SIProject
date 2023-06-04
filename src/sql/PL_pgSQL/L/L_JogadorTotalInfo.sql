CREATE OR REPLACE FUNCTION partidas_jogador(uid INTEGER) RETURNS INTEGER
AS
$$
DECLARE
    total_jogos_single integer;
    total_jogos_multi integer;
BEGIN
    if not exists(select * from players where id=uid) then
       RAISE  EXCEPTION  'Player doesnt exist';
    end if;    
	
	select  COUNT(DISTINCT(match_number)) from single_player_match where player_id = uid into total_jogos_single;
	
    SELECT COUNT(DISTINCT(pm.match_number))
	INTO total_jogos_multi
	FROM plays_multi AS pm
	LEFT JOIN multi_player_match AS mpm 
		ON pm.game_ref = mpm.game_ref 
		AND pm.match_number = mpm.match_number 
		AND mpm.state != 'Terminada'
	WHERE pm.player_id = uid AND mpm.game_ref IS NULL;
	
    RETURN total_jogos_multi + total_jogos_single;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE view jogador_total_info
as select p.id, p.state, p.email, p.username, total_jogos_jogador(p.id),partidas_jogador(p.id) ,total_pontos_jogador(p.id)
   from players p
   where p.state != 'Banned';

select jogador_total_info