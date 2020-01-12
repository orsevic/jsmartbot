-- liquibase formatted sql

--changeset orsevic:structure context:common failOnError:true
CREATE TABLE user_property (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v1() ,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(32) NOT NULL,
    parent_id UUID REFERENCES user_property(id)
);

CREATE TABLE question (
  id UUID PRIMARY KEY,
  text VARCHAR(2048) NOT NULL,
  user_property UUID REFERENCES user_property(id)
);

CREATE TABLE answer (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v1() ,
  text VARCHAR(2048) NOT NULL,
  question_id UUID NOT NULL REFERENCES question(id)
);

CREATE TABLE user_state(
    user_id VARCHAR(256) PRIMARY KEY,
    current_question_id UUID NOT NULL REFERENCES question(id)
);

CREATE TABLE user_answers (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v1() ,
    user_id VARCHAR(256) NOT NULL,
    question_id UUID NOT NULL REFERENCES question(id),
    answer_id UUID REFERENCES answer(id),
    answer_text VARCHAR(2048)
);

CREATE TABLE question_roadmap (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v1() ,
    question_id UUID NOT NULL REFERENCES question(id),
    start BOOLEAN NOT NULL DEFAULT false,
    answer_id UUID REFERENCES answer(id),
    answer_text VARCHAR(2048),
    next_question_id UUID NOT NULL REFERENCES question(id)
);

CREATE TABLE user_data (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v1(),
    user_id VARCHAR(256) NOT NULL,
    user_property UUID REFERENCES user_property(id),
    value VARCHAR(2048),
    parent_id UUID REFERENCES user_data(id)
);



--changeset orsevic:test_question context:test failOnError:true
insert into question(id, text) values
('9f20b0bb-a193-47f1-a05f-020dcd57cbb6', 'Hi. What is your name? '),
('49436879-c1dc-42f4-bec5-907e0875a93a', 'Nice to meet you, ${userData.userName}'),
('a36cc06b-0614-4282-a782-e1ed5085dec6', 'Which english level do you have?'),
('49436879-c1dc-42f4-bec5-907e0875a93a', 'Great! I have ${applicationData.}');


insert into answer(id, text, question_id) values
('31ae8062-744d-454b-a647-200708d339fd', 'beginner', 'a36cc06b-0614-4282-a782-e1ed5085dec6'),
('2441fb0f-b183-4e81-9d4a-59eaeec07b52', 'intermediate', 'a36cc06b-0614-4282-a782-e1ed5085dec6');

insert into question_roadmap(id, start, question_id, answer_id, answer_text, next_question_id) values
('1110b0bb-a193-47f1-a05f-020dcd57cbb6', true, '9f20b0bb-a193-47f1-a05f-020dcd57cbb6', null, 'Joe', '49436879-c1dc-42f4-bec5-907e0875a93a'),
('1110b0bb-a193-47f1-a05f-020dcd57cbb6', true, '9f20b0bb-a193-47f1-a05f-020dcd57cbb6', null, 'Joe', 'a36cc06b-0614-4282-a782-e1ed5085dec6');
