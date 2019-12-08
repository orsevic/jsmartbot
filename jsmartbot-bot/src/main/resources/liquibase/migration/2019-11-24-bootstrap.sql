-- liquibase formatted sql

--changeset orsevic:structure context:common failOnError:true
CREATE TABLE question (
  id UUID PRIMARY KEY,
  text VARCHAR(2048) NOT NULL
);

CREATE TABLE answer (
  id UUID PRIMARY KEY,
  text VARCHAR(2048) NOT NULL,
  question_id UUID NOT NULL REFERENCES question(id)
);

CREATE TABLE user_state(
    user_id VARCHAR(256) PRIMARY KEY,
    current_question_id UUID NOT NULL REFERENCES question(id)
);

CREATE TABLE user_answers (
    user_id VARCHAR(256) NOT NULL,
    question_id UUID NOT NULL REFERENCES question(id),
    answer_id UUID REFERENCES answer(id),
    answer_text VARCHAR(2048),
    CONSTRAINT pk_user_id_question_id PRIMARY KEY(user_id, question_id)
);

CREATE TABLE question_roadmap (
    id UUID PRIMARY KEY,
    question_id UUID NOT NULL REFERENCES question(id),
    answer_id UUID REFERENCES answer(id),
    answer_text VARCHAR(2048),
    next_question_id UUID NOT NULL REFERENCES question(id)
);

--changeset orsevic:test_question context:test failOnError:true
insert into question(id, text) values
('9f20b0bb-a193-47f1-a05f-020dcd57cbb6', 'Hi. What is your name? '),
('a36cc06b-0614-4282-a782-e1ed5085dec6', 'Which english level do you have?');


insert into answer(id, text, question_id) values
('31ae8062-744d-454b-a647-200708d339fd', 'beginner', 'a36cc06b-0614-4282-a782-e1ed5085dec6'),
('2441fb0f-b183-4e81-9d4a-59eaeec07b52', 'intermediate', 'a36cc06b-0614-4282-a782-e1ed5085dec6');
