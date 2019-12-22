package com.jsmartbot.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;


public class UserDto implements Serializable{
    private UUID id;
    private String telegramId;
    private Instant lastEntry;

    @JsonCreator
    public UserDto(@JsonProperty("id") UUID id, @JsonProperty("telegramId") String telegramId, @JsonProperty("lastEntry") Instant lastEntry){
        this.id = id;
        this.telegramId = telegramId;
        this.lastEntry = lastEntry;
    }

    public UUID getId() {
        return id;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public Instant getLastEntry() {
        return lastEntry;
    }
}
