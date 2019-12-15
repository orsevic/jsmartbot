package com.jsmartbot.bot.api.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sergeyorlov
 **/
public class AnswerQuestionDto  implements Serializable {
    private final String userId;
    private final UUID answerId;
    private final String anotherAnswer;

    public AnswerQuestionDto(@JsonProperty("userId") String userId, @JsonProperty("answerId") UUID answerId, @JsonProperty("anotherAnswer") String anotherAnswer) {
        this.userId = userId;
        this.answerId = answerId;
        this.anotherAnswer = anotherAnswer;
    }

    public String getUserId() {
        return userId;
    }

    public UUID getAnswerId() {
        return answerId;
    }

    public String getAnotherAnswer() {
        return anotherAnswer;
    }
}
