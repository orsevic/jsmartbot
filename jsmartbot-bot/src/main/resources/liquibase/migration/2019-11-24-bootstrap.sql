-- liquibase formatted sql

--changeset orsevic:structure context:common failOnError:true
CREATE TABLE question (
  id UUID PRIMARY KEY,
  text VARCHAR(2048) NOT NULL
);

CREATE TABLE answer (
  id UUID PRIMARY KEY,
  text VARCHAR(2048) NOT NULL,
  question_id UUID NOT NULL
);

--changeset orsevic:test_question context:test failOnError:true
insert into question(id, text) values
('9f20b0bb-a193-47f1-a05f-020dcd57cbb6', 'Hi. What is your name? '),
('a36cc06b-0614-4282-a782-e1ed5085dec6', 'Which english level do you have?');
