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
    private UUID questionId;
    @Nullable
    private UUID answerId;
    @Nullable
    private String answerText;
    private String nextQuestionSupplier;
    private boolean start;

    public PhraseRoadmap() {
    }

    public PhraseRoadmap(UUID id, UUID questionId, UUID answerId, String answerText, String nextQuestionSupplier, boolean start) {
        this.id = id;
        this.questionId = questionId;
        this.answerId = answerId;
        this.answerText = answerText;
        this.nextQuestionSupplier = nextQuestionSupplier;
        this.start = start;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
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

    public String getNextQuestionSupplier() {
        return nextQuestionSupplier;
    }

    public void setNextQuestionSupplier(String nextQuestionSupplier) {
        this.nextQuestionSupplier = nextQuestionSupplier;
    }

    public boolean isStart() {
        return start;
    }
}
