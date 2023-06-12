INSERT INTO regions(name)
VALUES ('North America'),
       ('Europe'),
       ('Asia'),
       ('South America'),
       ('Africa'),
       ('Australia');

-- Create the players table
INSERT INTO players (email, username, state, region_name)
VALUES ('marcorio@isel.pt', 'marsul', 'Ativo', 'Europe'),
       ('bernardo@isel.pt', 'bernardo', 'Ativo', 'Europe'),
       ('rafael@isel.pt', 'rafa', 'Ativo', 'Europe'),
       ('john@example.com', 'John123', 'Ativo', 'North America'),
       ('maria@example.com', 'Maria456', 'Ativo', 'South America'),
       ('peter@example.com', 'Peter789', 'Inativo', 'Europe'),
       ('jane@example.com', 'Jane101', 'Banido', 'Asia');

-- Create the
INSERT INTO game (reference, name, url)
VALUES ('G1', 'Chess', 'https://www.chess.com/'),
       ('G2', 'Checkers', 'https://www.checkers.com/'),
       ('G3', 'Monopoly', 'https://www.monopoly.com/'),
       ('G4', 'Risk', 'https://www.risk.com/'),
       ('G5', 'Scrabble', 'https://www.scrabble.com/');


INSERT INTO badges (name, game_ref, points_limit, image_url,version)
VALUES ('strong', 'G1', 1000, 'strong.com',1),
       ('best', 'G2', 1000, 'best.com',1),
       ('winner', 'G3', 10000, 'winner.com',1),
       ('war', 'G4', 5000, 'war.com',1),
       ('fight', 'G4', 1500, 'fight.com',1),
       ('test', 'G3', 1400, 'test.com',1);

INSERT INTO player_purchase (player_id, game_ref, purchase_date, price)
VALUES (1, 'G1', '2022-03-15 14:30:00', 9.99),
       (2, 'G2', '2022-02-28 10:15:00', 4.99),
       (3, 'G3', '2022-01-20 08:00:00', 19.99),
       (4, 'G4', '2022-04-02 16:45:00', 14.99),
       (5, 'G5', '2022-05-10 12:00:00', 7.99),
       (6, 'G1', '2022-04-30 20:00:00', 12.99),
       (7, 'G2', '2022-06-05 11:30:00', 5.99);


INSERT INTO matches (match_number, start_time, end_time, game_ref, region_name)
VALUES (1, '2022-03-15 15:00:00', '2022-03-15 16:30:00', 'G1', 'Europe'),
       (1, '2022-02-28 10:30:00', '2022-02-28 12:00:00', 'G2', 'Europe'),
       (1, '2022-01-20 09:00:00', '2022-01-20 10:30:00', 'G3', 'Europe'),
       (1, '2022-03-17 15:00:00', '2022-03-17 16:30:00', 'G4', 'Europe'),
       (2, '2022-03-18 15:00:00', '2022-03-18 16:30:00', 'G1', 'Europe'),
       (2, '2022-03-19 15:00:00', '2022-03-19 16:30:00', 'G2', 'Europe'),
       (2, '2022-03-20 15:00:00', NULL, 'G4', 'Europe'),
       (2, '2022-03-21 15:00:00', NULL, 'G3', 'Europe'),
       (3, '2022-03-11 11:00:00', '2022-03-11 11:00:10', 'G1', 'Europe');

-- Insert new single player matches with their difficulty level and points
INSERT INTO single_player_match (match_number, game_ref, player_id, difficulty, points)
VALUES (1, 'G1', 1, 1,10000),
       (1, 'G3', 3, 5,6000),
       (2, 'G1', 2, 3,8000),
       (2, 'G2', 3, 5,12000),
       (2, 'G3', 2, 4,15000),
       (3, 'G1', 2, 3,1000);

-- Insert new multi player matches with their state
INSERT INTO multi_player_match (match_number, game_ref, state)
VALUES (1, 'G2', 'Por iniciar'),
       (1, 'G4', 'Em curso'),
       (2, 'G4', 'Terminada');

-- Insert new plays points matches with their points
INSERT INTO plays_multi (match_number, game_ref, player_id, points)
VALUES (1, 'G4', 1, 7000),
       (1, 'G4', 2, 6500),
       (1, 'G4', 3, 6250),
       (2, 'G4', 7, 1000);

INSERT INTO friends (player_id, friend_id)
VALUES (1, 2),
       (1, 3),
       (2, 3);

INSERT INTO conversations (id, name)
VALUES (1, 'the life'),
       (2, 'the origin'),
       (3, 'isel is good');

INSERT INTO players_conversation (player_id, conversations_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (2, 2),
       (3, 2),
       (3, 3),
       (1, 3);

INSERT INTO messages (message_number, sent_time, text, conversation_id, player_id)
VALUES (1, '2022-03-15 15:00:00', 'Why don''t scientists trust atoms? Because they make up everything.', 1, 2),
       (2, '2022-03-15 15:00:00', 'What do you call fake spaghetti? An impasta.', 1, 3),
       (3, '2022-03-15 15:00:00', 'I''m reading a book on anti-gravity. It''s impossible to put down', 1, 1),
       (1, '2022-03-15 15:00:00', 'O que a galinha perguntou ao galo ? croo', 3, 3),
       (1, '2022-03-15 15:00:00', 'Quantos dentes tem um burro ?', 2, 2);
