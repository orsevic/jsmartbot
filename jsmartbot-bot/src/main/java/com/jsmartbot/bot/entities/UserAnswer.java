package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class UserAnswer {
    @Id
    private UUID id;
    private String userId;
    private UUID phraseId;
    private UUID answerId;
    private String text;

    public UserAnswer(UUID id, String userId, UUID phraseId, UUID answerId, String text) {
        this.id = id;
        this.userId = userId;
        this.phraseId = phraseId;
        this.answerId = answerId;
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(UUID phraseId) {
        this.phraseId = phraseId;
    }

    public UUID getAnswerId() {
        return answerId;
    }

    public void setAnswerId(UUID answerId) {
        this.answerId = answerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
