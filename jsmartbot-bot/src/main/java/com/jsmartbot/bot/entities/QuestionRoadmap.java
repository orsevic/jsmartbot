package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class QuestionRoadmap {
    @Id
    private UUID id;
    private UUID questionId;
    private UUID answerId;
    private String answerText;
    private UUID nextQuestionId;
    private boolean start;

    public QuestionRoadmap(UUID id, UUID questionId, UUID answerId, String answerText, UUID nextQuestionId, boolean start) {
        this.id = id;
        this.questionId = questionId;
        this.answerId = answerId;
        this.answerText = answerText;
        this.nextQuestionId = nextQuestionId;
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

    public UUID getAnswerId() {
        return answerId;
    }

    public void setAnswerId(UUID answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public UUID getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(UUID nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

    public boolean isStart() {
        return start;
    }
}
