-- Assume-se a existência de um sistema que atualiza a pontuação e estado durante a execução do jogo
--Cada partida está associada a uma região e apenas jogadores dessa região a podem jogar


-- o  é criada uma partida com um número sequencial e único para cada jogo
CREATE OR REPLACE FUNCTION next_match_number_trigger()
    RETURNS TRIGGER AS $$
    DECLARE
        max_match_number INT;
    BEGIN
        select MAX(match_number) INTO max_match_number from matches where game_ref = NEW.game_ref;
          IF max_match_number IS NULL THEN
              NEW.match_number := 1;
        ELSE
            NEW.match_number := max_match_number+1;
        END IF;
          RETURN NEW;
    END;
    $$ LANGUAGE plpgsql;


    ----------------------------TRIGGERS----------------------------
    CREATE OR REPLACE TRIGGER match_number_setter
        BEFORE INSERT ON matches
        FOR EACH ROW
            EXECUTE FUNCTION next_match_number_trigger();


CREATE OR REPLACE FUNCTION next_match_number(
    reference varchar
)
    RETURNS INT AS $$
DECLARE
    max_match_number INT;
BEGIN
    select MAX(match_number) INTO max_match_number from matches where game_ref = reference;
    IF max_match_number IS NULL THEN
        max_match_number := 1;
    ELSE
        max_match_number := max_match_number + 1;
    END IF;
    RETURN max_match_number;
END;
$$ LANGUAGE plpgsql;


create or replace function f_opt_lock() returns trigger
language plpgsql as $$
begin
if TG_OP = 'INSERT' and new.version is null then
new.version = 1; -- é o que o JPA coloca no INSERT
elseif TG_OP = 'UPDATE' then --and new.vers != old.vers+1 then
-- pode não se ter atualizado a versão
-- ou a atualização pode não ter sido com +1
-- assume-se que o JPA incrementa com +1
new.version = old.version + 1;
end if;
return new;
end; $$;
CREATE or replace TRIGGER GL_Opt
BEFORE insert or UPDATE on badges
                            FOR EACH ROW
                            execute function f_opt_lock();

UPDATE badges
SET points_limit = 2000
WHERE name = 'strong' and game_ref = 'G1';