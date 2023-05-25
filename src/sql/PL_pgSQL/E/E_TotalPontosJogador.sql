CREATE OR REPLACE FUNCTION total_pontos_jogador(uid INTEGER) 
RETURNS INTEGER
AS $$
DECLARE
    total_pontos_single integer;
    total_pontos_multi integer;
BEGIN
	
    IF NOT EXISTS(SELECT * FROM players WHERE id=uid) THEN
       RAISE EXCEPTION 'Player does not exist';
    END IF;
	
	select  COALESCE(SUM(points),0) from single_player_match where player_id = uid into total_pontos_single;
	
    SELECT COALESCE(SUM(pm.points), 0)
	INTO total_pontos_multi
	FROM plays_multi AS pm
	LEFT JOIN multi_player_match AS mpm 
		ON pm.game_ref = mpm.game_ref 
		AND pm.match_number = mpm.match_number 
		AND mpm.state != 'Terminada'
	WHERE pm.player_id = uid AND mpm.game_ref IS NULL;

	RETURN total_pontos_multi + total_pontos_single;
END;
$$ LANGUAGE plpgsql;