

CREATE OR REPLACE FUNCTION banir_jogador()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE players
    SET state = 'Banido'
    WHERE id = OLD.id;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER banir_jogador_trigger
    INSTEAD OF DELETE ON jogadorTotalInfo
    FOR EACH ROW
EXECUTE FUNCTION banir_jogador();
