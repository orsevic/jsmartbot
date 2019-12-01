package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Answer {
    @Id
    public UUID id;
    public String text;
    public UUID questionId;
}
