package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Answer {
    @Id
    private UUID id;
    private String text;
    private UUID phraseId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(UUID phraseId) {
        this.phraseId = phraseId;
    }
}
