package com.jsmartbot.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;


public class UserDto implements Serializable{
    private final UUID id;
    private final String telegramId;
    private final String firstName;
    private final String lastName;
    private final String telegramUserName;
    private final Instant lastEntry;

    @JsonCreator
    public UserDto(
            @JsonProperty("id") UUID id,
            @JsonProperty("telegramId") String telegramId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("telegramUserName") String telegramUserName,
            @JsonProperty("lastEntry") Instant lastEntry
    ){
        this.id = id;
        this.telegramId = telegramId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telegramUserName = telegramUserName;
        this.lastEntry = lastEntry;
    }

    public UUID getId() {
        return id;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelegramUserName() {
        return telegramUserName;
    }

    public Instant getLastEntry() {
        return lastEntry;
    }
}
