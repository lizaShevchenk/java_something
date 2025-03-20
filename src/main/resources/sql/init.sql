CREATE SCHEMA IF NOT EXISTS libSchema;

CREATE USER libUser WITH ENCRYPTED PASSWORD 'libPwd';

GRANT ALL ON SCHEMA libSchema TO libUser;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA libSchema TO libUser;

CREATE TABLE libSchema.author(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(500),
    last_name VARCHAR(500),
    email VARCHAR(500) NOT NULL
);

CREATE TABLE libSchema.book(
    id SERIAL PRIMARY KEY,
    name VARCHAR(500),
    count_pages INT,
    author_id INT NOT NULL,
    FOREIGN KEY(author_id)
        REFERENCES libSchema.author
);

CREATE TABLE libSchema.journal(
    id SERIAL PRIMARY KEY,
    name VARCHAR(500),
    count_pages INT,
    number INT,
    publication_year INT
);