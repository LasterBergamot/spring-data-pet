CREATE TABLE IF NOT EXISTS trivia (
    id                  SMALLINT        GENERATED ALWAYS AS IDENTITY    PRIMARY KEY,
    category            text                                     NOT NULL,
    type                text                                     NOT NULL,
    difficulty          text                                     NOT NULL,
    question            text                                     NOT NULL,
    correct_answer      text                                     NOT NULL,
    incorrect_answers   text[]                                   NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id              SMALLINT        GENERATED ALWAYS AS IDENTITY    PRIMARY KEY,
    first_name      text                                            NOT NULL,
    middle_name     text,
    last_name       text                                            NOT NULL
);

CREATE TABLE IF NOT EXISTS phone_numbers (
    user_id         SMALLINT    REFERENCES users (id)   NOT NULL,
    phone_number    text                                NOT NULL    UNIQUE
);

CREATE TABLE IF NOT EXISTS answers (
    id                  SMALLINT    GENERATED ALWAYS AS IDENTITY    PRIMARY KEY,
    user_id             SMALLINT    REFERENCES users (id)           NOT NULL,
    trivia_id           SMALLINT    REFERENCES trivia (id)          NOT NULL,
    selected_answer     text                                        NOT NULL,
    answered_correctly  BOOLEAN                                     NOT NULL
);