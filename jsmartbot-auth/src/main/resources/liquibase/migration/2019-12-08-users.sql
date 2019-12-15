-- liquibase formatted sql

--changeset ksstolpnik:structure context:common failOnError:true
CREATE TABLE account(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v1() ,
    telegram_id VARCHAR(256) NOT NULL UNIQUE ,
    last_entry TIMESTAMP NOT NULL
);

