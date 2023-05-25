CREATE OR REPLACE FUNCTION pontos_jogo_por_jogador(game_reference VARCHAR(10))
    RETURNS TABLE (
        player_id INTEGER,
        total_points BIGINT
    )
AS
$$
BEGIN
    IF NOT EXISTS(SELECT * FROM game WHERE reference = game_reference) THEN
        RAISE EXCEPTION 'Game does not exist';
    END IF;
    RETURN QUERY
        SELECT pm.player_id AS player_id, SUM(pm.points) AS total_points
        FROM (
            SELECT spm.player_id, spm.points, spm.game_ref, spm.match_number
            FROM single_player_match as spm
            WHERE game_ref = game_reference
            UNION ALL
            SELECT pm.player_id, pm.points, pm.game_ref, pm.match_number
            FROM plays_multi as pm
            WHERE pm.game_ref = game_reference
        ) pm
        LEFT JOIN multi_player_match mpm 
            ON pm.game_ref = mpm.game_ref 
            AND pm.match_number = mpm.match_number 
            AND mpm.state != 'Terminada'
        WHERE mpm.game_ref IS NULL
        GROUP BY pm.player_id;
END;
$$ LANGUAGE plpgsql;

	