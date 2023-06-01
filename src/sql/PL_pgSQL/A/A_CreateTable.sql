-- Create the regions table
CREATE TABLE regions
(
    name VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY CHECK(NAME IN (('North America'),
       ('Europe'),
       ('Asia'),
       ('South America'),
       ('Africa'),
       ('Australia')
       ))
);

-- Create the players table
CREATE TABLE players
(
    id          SERIAL PRIMARY KEY,
    email       VARCHAR(255) UNIQUE NOT NULL,
    username    VARCHAR(255) UNIQUE NOT NULL,
    state       VARCHAR(20)         NOT NULL,
    region_name VARCHAR(255) REFERENCES regions (name),
    CONSTRAINT check_state CHECK (state IN ('Ativo', 'Inativo', 'Banido'))
);

-- Create the games table
CREATE TABLE game
(
    reference   VARCHAR(10) PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    url  VARCHAR(255)        NOT NULL
);

-- Create the player_purchase table to track player purchases
CREATE TABLE player_purchase
(
    player_id     INTEGER REFERENCES players (id),
    game_ref       VARCHAR(10) REFERENCES game (reference),
    purchase_date TIMESTAMP      NOT NULL,
    price         NUMERIC(10, 2) NOT NULL,
    PRIMARY KEY (player_id, game_ref)
);

-- Create the updated matches table
CREATE TABLE matches
(
    match_number INTEGER, --criada uma partida com um número sequencial e único para cada jogo
    start_time   TIMESTAMP   NOT NULL,
    end_time     TIMESTAMP,
    game_ref     VARCHAR(10) REFERENCES game (reference),
    region_name  VARCHAR(255) REFERENCES regions (name),
    PRIMARY KEY (match_number, game_ref)
);

CREATE TABLE single_player_match
(
    match_number INTEGER,
	game_ref VARCHAR(10),
    player_id INTEGER REFERENCES players(id),
    difficulty INTEGER NOT NULL,
    points INTEGER,
    CONSTRAINT check_difficulty CHECK (difficulty BETWEEN 1 AND 5), --Tirar o IN
    PRIMARY KEY (match_number,game_ref), --  (match_number,player_id) alteração necessaria
    FOREIGN KEY (match_number, game_ref) REFERENCES matches (match_number, game_ref) ON DELETE CASCADE,
    FOREIGN KEY (player_id) REFERENCES players (id) ON DELETE CASCADE
);

CREATE TABLE multi_player_match
(
    match_number INTEGER,
	game_ref VARCHAR(10) NOT NULL,
    state        varchar(50) NOT NULL,
    CONSTRAINT check_state CHECK (state IN ('Por iniciar', 'A aguardar jogadores', 'Em curso', 'Terminada')),
    PRIMARY KEY (match_number,game_ref), -- PRIMARY KEY (match_number), altereção feito
    FOREIGN KEY (match_number, game_ref) REFERENCES matches (match_number, game_ref) ON DELETE CASCADE
);

CREATE TABLE plays_multi
(
    match_number INTEGER NOT NULL,
	game_ref VARCHAR(10) NOT NULL,
    player_id    INTEGER NOT NULL REFERENCES players (id),
    points       INTEGER,
    PRIMARY KEY (match_number, player_id),
    FOREIGN KEY (match_number, game_ref) REFERENCES matches (match_number, game_ref) ON DELETE CASCADE
);

-- Create the badges table
CREATE TABLE badges
(
    name         VARCHAR(255) UNIQUE NOT NULL,
    game_ref      VARCHAR(10) REFERENCES game (reference),
    points_limit INTEGER             NOT NULL,
    image_url    VARCHAR(255)        NOT NULL,
    PRIMARY KEY (name, game_ref)
);

-- Create the player_badge table to track badges earned by players
CREATE TABLE player_badge
(
    game_ref varchar (10),
    badge_name VARCHAR(255),
    player_id  INTEGER REFERENCES players (id),
    PRIMARY KEY (game_ref,badge_name,player_id),
    FOREIGN KEY (game_ref,badge_name) REFERENCES badges (game_ref,name)

);

-- Player stats
CREATE TABLE player_stats
(
    id               SERIAL PRIMARY KEY,
    player_id        INTEGER REFERENCES players (id),
    num_matches      INTEGER,
    num_games_played INTEGER,
    total_points     INTEGER
);

-- Game stats
CREATE TABLE game_stats
(
    id           SERIAL PRIMARY KEY,
    game_ref      VARCHAR(10) REFERENCES game (reference),
    num_matches  INTEGER,
    num_players  INTEGER,
    total_points INTEGER
);

-- Create the friends table to track player friendships
CREATE TABLE friends
(
    player_id INTEGER REFERENCES players (id),
    friend_id INTEGER REFERENCES players (id),
    PRIMARY KEY (player_id, friend_id)
);

-- Create the conversations table
CREATE TABLE conversations
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE players_conversation
(
    player_id        INTEGER REFERENCES players (id),
    conversations_id INTEGER REFERENCES conversations (id),
    primary key (player_id, conversations_id)
);

-- Create the messages table to track messages in conversations
CREATE TABLE messages
(
    message_number  INTEGER       NOT NULL,
    sent_time       TIMESTAMP     NOT NULL,
    text            VARCHAR(1000) NOT NULL,
    conversation_id INTEGER REFERENCES conversations (id),
    player_id       INTEGER REFERENCES players (id),
    primary key (message_number, conversation_id)
);
