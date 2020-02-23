package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class UserState {
    @Id
    private String userId;
    private UUID currentPhraseId;

    public UserState() {
    }

    public UserState(String userId, UUID currentPhraseId) {
        this.userId = userId;
        this.currentPhraseId = currentPhraseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getCurrentPhraseId() {
        return currentPhraseId;
    }

    public void setCurrentPhraseId(UUID currentPhraseId) {
        this.currentPhraseId = currentPhraseId;
    }
}
