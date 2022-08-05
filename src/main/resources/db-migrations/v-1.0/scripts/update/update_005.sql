CREATE TABLE users (
    id       BIGSERIAL NOT NULL PRIMARY KEY,
    name     VARCHAR NOT NULL,
    email    VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    phone    VARCHAR NOT NULL
);