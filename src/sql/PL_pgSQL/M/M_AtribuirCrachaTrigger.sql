
CREATE OR REPLACE FUNCTION atribuir_crachas_trigger() RETURNS TRIGGER AS $$
DECLARE
    badges_from_the_game  varchar(255) array;
    players_ids integer array;
    badges_name varchar(255);
    player_id_ integer;
    player_id_sp integer;
BEGIN
     IF old.end_time is not null and new.end_time IS  NULL THEN
         Raise EXCEPTION  'Nao tem jogos novos para confirmar';
     end if;

     select array  (select DISTINCT name from badges where game_ref = NEW.game_ref into badges_from_the_game);

     select player_id from single_player_match where match_number = NEW.match_number into player_id_sp;

     select array  (select DISTINCT player_id from plays_multi where match_number = NEW.match_number into players_ids);

     IF player_id_sp is not null then
         FOREACH badges_name in ARRAY badges_from_the_game loop
                 raise notice 'badges_name %', badges_name;
                 raise notice 'player_id_sp %', player_id_sp;
                 call associar_cracha(player_id_sp, NEW.game_ref,badges_name);
             end loop;
     ELSE
         FOREACH badges_name in ARRAY badges_from_the_game loop
                 FOREACH player_id_ in ARRAY players_ids loop
                         raise notice 'players_ids %', player_id_;
                         raise notice 'badges_name %', badges_name;
                     call associar_cracha(player_id_, NEW.game_ref,badges_name);
                 end loop;
             end loop;
     END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE or replace TRIGGER atribuir_crachas_trigger
    AFTER UPDATE ON matches
    FOR EACH ROW
EXECUTE FUNCTION atribuir_crachas_trigger();

