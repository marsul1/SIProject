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