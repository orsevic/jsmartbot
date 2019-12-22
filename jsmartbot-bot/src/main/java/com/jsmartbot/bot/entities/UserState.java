package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class UserState {
    @Id
    private String userId;
    private UUID currentQuestionId;

    public UserState() {
    }

    public UserState(String userId, UUID currentQuestionId) {
        this.userId = userId;
        this.currentQuestionId = currentQuestionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getCurrentQuestionId() {
        return currentQuestionId;
    }

    public void setCurrentQuestionId(UUID currentQuestionId) {
        this.currentQuestionId = currentQuestionId;
    }
}
