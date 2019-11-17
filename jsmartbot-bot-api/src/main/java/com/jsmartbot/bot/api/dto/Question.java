package com.jsmartbot.bot.api.dto;

import java.util.UUID;

public class Question {
    public UUID id;
    public String text;

    public Question(String text) {
        this.text = text;
    }
}
