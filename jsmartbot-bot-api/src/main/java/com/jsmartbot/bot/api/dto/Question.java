package com.jsmartbot.bot.api.dto;

import java.io.Serializable;
import java.util.UUID;

public class Question implements Serializable {
    public UUID id;
    public String text;

    public Question(String text) {
        this.text = text;
    }
}
