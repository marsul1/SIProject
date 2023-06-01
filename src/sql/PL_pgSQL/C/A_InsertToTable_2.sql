INSERT INTO regions(name)
VALUES ('North America'),
       ('Europe'),
       ('Asia'),
       ('South America'),
       ('Africa'),
       ('Australia');

-- Create the players table
-- INSERT INTO players (id, email, username, state, region_name)
-- VALUES (1, 'marcorio@isel.pt', 'marsul', 'Ativo', 'Europe'),
--        (2, 'bernardo@isel.pt', 'bernardo', 'Ativo', 'Europe'),
--        (3, 'rafael@isel.pt', 'rafa', 'Ativo', 'Europe'),
--        (4, 'john@example.com', 'John123', 'Ativo', 'North America'),
--        (5, 'maria@example.com', 'Maria456', 'Ativo', 'South America'),
--        (6, 'peter@example.com', 'Peter789', 'Inativo', 'Europe'),
--        (7, 'jane@example.com', 'Jane101', 'Banido', 'Asia');

-- Create the
INSERT INTO game (reference, name, url)
VALUES ('G1', 'Chess', 'https://www.chess.com/'),
       ('G2', 'Checkers', 'https://www.checkers.com/'),
       ('G3', 'Monopoly', 'https://www.monopoly.com/'),
       ('G4', 'Risk', 'https://www.risk.com/'),
       ('G5', 'Scrabble', 'https://www.scrabble.com/');


INSERT INTO badges (name, game_ref, points_limit, image_url)
VALUES ('strong', 'G1', 1000, 'strong.com'),
       ('best', 'G2', 1000, 'best.com'),
       ('winner', 'G3', 10000, 'winner.com'),
       ('war', 'G4', 5000, 'war.com'),
       ('fight', 'G4', 1500, 'fight.com'),
       ('test', 'G3', 1400, 'test.com');





