package com.jsmartbot.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class AnswerDto  implements Serializable {
    private final UUID id;
    private final String text;

    @JsonCreator
    public AnswerDto(@JsonProperty("id") UUID id, @JsonProperty("text") String text) {
        this.id = id;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
