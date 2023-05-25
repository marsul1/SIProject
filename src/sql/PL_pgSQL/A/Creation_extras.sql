-- Assume-se a existência de um sistema que atualiza a pontuação e estado durante a execução do jogo
--Cada partida está associada a uma região e apenas jogadores dessa região a podem jogar


-- o  é criada uma partida com um número sequencial e único para cada jogo
CREATE OR REPLACE FUNCTION next_id()
    RETURNS TRIGGER AS $$
    DECLARE
        max_id INT;
    BEGIN
        select MAX(id) INTO max_id from partida where jogo = NEW.jogo;
          IF max_id IS NULL THEN
              NEW.id := 1;
        ELSE
            NEW.id := max_id+1;
        END IF;
          RETURN NEW;
    END;
    $$ LANGUAGE plpgsql;


    ----------------------------TRIGGERS----------------------------
    CREATE OR REPLACE TRIGGER partida_id_setter
        BEFORE INSERT ON PARTIDA
        FOR EACH ROW
            EXECUTE FUNCTION next_id();