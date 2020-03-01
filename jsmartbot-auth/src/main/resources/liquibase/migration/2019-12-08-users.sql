-- liquibase formatted sql

--changeset ksstolpnik:structure context:common failOnError:true
CREATE TABLE account(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4() ,
    telegram_id VARCHAR(32) NOT NULL UNIQUE ,
    first_name VARCHAR(64),
    last_name VARCHAR(64),
    telegram_user_name VARCHAR(64),
    last_entry TIMESTAMP NOT NULL
);

