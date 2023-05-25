create or replace procedure associar_cracha_logic(
    uid integer, -- id do jogador
    _ref varchar(10), -- referecia do jogo
    nome_cracha varchar(255) -- nome do cracha
)
as
$$
declare
    check_player_badge  varchar(255);
    total_player_pontos integer;
    check_points_limit  integer;
begin
    IF EXISTS (
    SELECT 1
    FROM player_badge as pb
    WHERE player_id = uid
      AND pb.game_ref = _ref
      AND pb.badge_name = nome_cracha
	) THEN
		RAISE EXCEPTION 'Player Already Has this badge';
	END IF;


    select total_points from pontos_jogo_por_jogador(_ref) where player_id = uid into total_player_pontos;

    select points_limit from badges b
		where b.game_ref = _ref
		  and b.name = nome_cracha
    into check_points_limit;
	
    if (total_player_pontos >= check_points_limit) then
        insert into player_badge(game_ref,  badge_name,player_id)
        VALUES (_ref, nome_cracha, uid);
	else
		RAISE EXCEPTION 'Player doesnt have enough points';
    end if;
end;
$$ language plpgsql;

CREATE OR REPLACE PROCEDURE associar_cracha(
    uid integer, -- id do jogador
    game_ref varchar(10), -- referecia do jogo
    nome_cracha varchar(255) -- nome do cracha
)
AS $$
DECLARE
    code char(5) DEFAULT '00000';
    msg text;
BEGIN
	rollback;
	set transaction isolation level read committed;
    BEGIN
	CALL associar_cracha_logic(uid, game_ref, nome_cracha);
	EXCEPTION
		WHEN OTHERS THEN
			GET STACKED DIAGNOSTICS
				code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;
			RAISE NOTICE 'code=%, msg=%', code, msg;
			ROLLBACK;
	END;
END;
$$ LANGUAGE plpgsql;