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
    @Nullable
    private UUID answerId;
    @Nullable
    private String answerText;
    private String nextPhraseSupplier;
    private boolean start;

    public PhraseRoadmap() {
    }

    public PhraseRoadmap(UUID id, UUID phraseId, UUID answerId, String answerText, String nextPhraseSupplier, boolean start) {
        this.id = id;
        this.phraseId = phraseId;
        this.answerId = answerId;
        this.answerText = answerText;
        this.nextPhraseSupplier = nextPhraseSupplier;
        this.start = start;
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

    public Optional<UUID> getAnswerId() {
        return Optional.ofNullable(answerId);
    }

    public void setAnswerId(UUID answerId) {
        this.answerId = answerId;
    }

    public Optional<String> getAnswerText() {
        return Optional.ofNullable(answerText);
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
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
}
