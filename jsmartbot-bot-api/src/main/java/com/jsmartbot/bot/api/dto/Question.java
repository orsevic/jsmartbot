package com.jsmartbot.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class Question implements Serializable {
    public final UUID id;
    public final String text;

    @JsonCreator
    public Question(@JsonProperty("id") UUID id, @JsonProperty("text") String text) {
        this.id = id;
        this.text = text;
    }
}
