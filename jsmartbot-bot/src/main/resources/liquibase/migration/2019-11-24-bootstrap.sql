-- liquibase formatted sql

--changeset orsevic:structure context:common failOnError:true
CREATE TABLE user_property (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4() ,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(32) NOT NULL,
    parent_id UUID REFERENCES user_property(id)
);

CREATE TABLE phrase (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  type VARCHAR(32) NOT NULL,
  text VARCHAR(2048) NOT NULL,
  params_supplier TEXT
);

CREATE TABLE answer (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4() ,
  text VARCHAR(2048) NOT NULL,
  phrase_id UUID NOT NULL REFERENCES phrase(id)
);

CREATE TABLE user_state(
    user_id VARCHAR(256) PRIMARY KEY,
    current_phrase_id UUID NOT NULL REFERENCES phrase (id)
);

CREATE TABLE user_answers (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4() ,
    user_id VARCHAR(256) NOT NULL,
    phrase_id UUID NOT NULL REFERENCES phrase (id),
    answer_id UUID REFERENCES answer(id),
    answer_text VARCHAR(2048)
);

CREATE TABLE phrase_roadmap (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4() ,
    phrase_id UUID NOT NULL REFERENCES phrase (id),
    start BOOLEAN NOT NULL DEFAULT false,
    next_phrase_supplier VARCHAR(2048),
    user_property VARCHAR(32)
);

CREATE TABLE user_data (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    property_id UUID REFERENCES user_property(id),
    value VARCHAR(2048),
    parent_id UUID REFERENCES user_data(id)
);



--changeset orsevic:test_question context:test failOnError:true
insert into user_property (id, name, type, parent_id) values
('f2340234-4ea9-4436-b8e3-d6aac5fce1b9', 'englishLevel', 'string', null),
('ae2dc4f0-01a9-49b9-ba9f-936d90f1ed50', 'login', 'string', null);

insert into phrase(id, type, text, params_supplier) values
('9f20b0bb-a193-47f1-a05f-020dcd57cbb6', 'QUESTION', 'Hi. What is your name?', null),
('49436879-c1dc-42f4-bec5-907e0875a93a', 'PHRASE', 'Nice to meet you, ${params.userName}', 'var result = {userName:userDataService.get(context.userId, null, "login").getValue()}; result;'),
('a36cc06b-0614-4282-a782-e1ed5085dec6', 'QUESTION', 'Which english level do you have?', null),
('c47a1123-3ed5-43fc-a67c-d5e02f8be179', 'PHRASE', 'Great! I have ${params.count}', 'var result = {count:1507}; result;');


insert into answer(id, text, phrase_id) values
('31ae8062-744d-454b-a647-200708d339fd', 'beginner', 'a36cc06b-0614-4282-a782-e1ed5085dec6'),
('2441fb0f-b183-4e81-9d4a-59eaeec07b52', 'intermediate', 'a36cc06b-0614-4282-a782-e1ed5085dec6');

insert into phrase_roadmap(id, start, phrase_id, next_phrase_supplier, user_property) values
('1110b0bb-a193-47f1-a05f-020dcd57cbb6', true, '9f20b0bb-a193-47f1-a05f-020dcd57cbb6', 'var result = "49436879-c1dc-42f4-bec5-907e0875a93a"; result;', 'login'),
('b7b6118e-d511-43b4-ab94-39c0107b0952', false, 'a36cc06b-0614-4282-a782-e1ed5085dec6', 'var result = "c47a1123-3ed5-43fc-a67c-d5e02f8be179"; result;', 'englishLevel'),
('1120b0bb-a193-47f1-a05f-020dcd57cbb6', false, '49436879-c1dc-42f4-bec5-907e0875a93a', 'var result = "a36cc06b-0614-4282-a782-e1ed5085dec6"; result;', null);
