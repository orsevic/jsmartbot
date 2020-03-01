package com.jsmartbot.bot.api.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sergeyorlov
 **/
public class ReplyDto implements Serializable {
    private final UUID userId;
    private final UUID answerId;
    private final String anotherAnswer;

    @JsonCreator
    public ReplyDto(@JsonProperty("userId") UUID userId, @JsonProperty("answerId") UUID answerId, @JsonProperty("anotherAnswer") String anotherAnswer) {
        this.userId = userId;
        this.answerId = answerId;
        this.anotherAnswer = anotherAnswer;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getAnswerId() {
        return answerId;
    }

    public String getAnotherAnswer() {
        return anotherAnswer;
    }
}
