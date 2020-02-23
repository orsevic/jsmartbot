package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Optional;
import java.util.UUID;

import org.springframework.lang.Nullable;

@Entity
public class PhraseRoadmap {
    @Id
    private UUID id;
    private UUID phraseId;
    private String nextPhraseSupplier;
    private boolean start;
    private String userProperty;

    public PhraseRoadmap() {
    }

    public PhraseRoadmap(UUID id, UUID phraseId, UUID answerId, String answerText, String nextPhraseSupplier, boolean start, String userProperty) {
        this.id = id;
        this.phraseId = phraseId;
        this.nextPhraseSupplier = nextPhraseSupplier;
        this.start = start;
        this.userProperty = userProperty;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(UUID phraseId) {
        this.phraseId = phraseId;
    }

    public String getNextPhraseSupplier() {
        return nextPhraseSupplier;
    }

    public void setNextPhraseSupplier(String nextPhraseSupplier) {
        this.nextPhraseSupplier = nextPhraseSupplier;
    }

    public boolean isStart() {
        return start;
    }

    public String getUserProperty() {
        return userProperty;
    }
}
