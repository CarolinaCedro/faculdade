CREATE TABLE users (
                       id VARCHAR(255) PRIMARY KEY NOT NULL,
                       login VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL
);
