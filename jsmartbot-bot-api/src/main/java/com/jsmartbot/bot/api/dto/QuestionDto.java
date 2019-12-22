package com.jsmartbot.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class QuestionDto implements Serializable {
    private final UUID id;
    private final String text;
    private final List<AnswerDto> answers;

    @JsonCreator
    public QuestionDto(@JsonProperty("id") UUID id, @JsonProperty("text") String text, @JsonProperty("answers") List<AnswerDto> answers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<AnswerDto> getAnswers() {
        return answers != null ? answers : Collections.emptyList();
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                '}';
    }
}
