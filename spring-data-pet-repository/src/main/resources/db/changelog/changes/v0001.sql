ALTER TABLE users ADD email text;

ALTER TABLE users ADD UNIQUE (email);