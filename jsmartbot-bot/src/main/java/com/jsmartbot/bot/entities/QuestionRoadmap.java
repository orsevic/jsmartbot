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
    private String text;
    private UUID nextQuestionId;

    public QuestionRoadmap(UUID id, UUID questionId, UUID answerId, String text, UUID nextQuestionId) {
        this.id = id;
        this.questionId = questionId;
        this.answerId = answerId;
        this.text = text;
        this.nextQuestionId = nextQuestionId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(UUID nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }
}
