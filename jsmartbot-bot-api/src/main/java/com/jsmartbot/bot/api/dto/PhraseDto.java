package com.jsmartbot.bot.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PhraseDto implements Serializable {
    private final UUID id;
    private final String text;
    private final String type;
    private final List<AnswerDto> answers;
    private final List<UUID> selectedUsers;

    @JsonCreator
    public PhraseDto(@JsonProperty("id") UUID id, @JsonProperty("text") String text, @JsonProperty("type") String type, @JsonProperty("answers") List<AnswerDto> answers, @JsonProperty("selectedUsers") List<UUID> selectedUsers) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.answers = answers;
        this.selectedUsers = selectedUsers;
    }

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<AnswerDto> getAnswers() {
        return answers != null ? answers : Collections.emptyList();
    }

    public String getType() {
        return type;
    }

    public List<UUID> getSelectedUsers() {
        return selectedUsers;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                '}';
    }
}
