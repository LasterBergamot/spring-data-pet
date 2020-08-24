CREATE TABLE IF NOT EXISTS trivia (
    id                  SMALLINT        GENERATED ALWAYS AS IDENTITY    PRIMARY KEY,
    category            VARCHAR(64)                                     NOT NULL,
    type                VARCHAR(32)                                     NOT NULL,
    difficulty          VARCHAR(16)                                     NOT NULL,
    question            VARCHAR(128)                                    NOT NULL,
    correct_answer      VARCHAR(32)                                     NOT NULL,
    incorrect_answers   VARCHAR(32)[]                                   NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id              SMALLINT        GENERATED ALWAYS AS IDENTITY    PRIMARY KEY,
    first_name      VARCHAR(64)                                     NOT NULL,
    middle_name     VARCHAR(64),
    last_name       VARCHAR(64)                                     NOT NULL,
    phone_numbers   VARCHAR(32)[]                                   NOT NULL,
    answer_ids      INTEGER[]       REFERENCES answers (id)
);

CREATE TABLE IF NOT EXISTS phone_numbers (
    user_id         SMALLINT    REFERENCES users (id)   NOT NULL,
    phone_number    VARCHAR(32)                         NOT NULL    UNIQUE
);

CREATE TABLE IF NOT EXISTS answers (
    id                  SMALLINT    GENERATED ALWAYS AS IDENTITY    PRIMARY KEY,
    user_id             SMALLINT    REFERENCES users (id)           NOT NULL,
    trivia_id           SMALLINT    REFERENCES trivia (id)          NOT NULL,
    selected_answer     VARCHAR(32)                                 NOT NULL,
    answered_correctly  BOOLEAN                                     NOT NULL
);