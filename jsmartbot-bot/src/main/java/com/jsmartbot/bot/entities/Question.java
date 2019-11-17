package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Question {
    public UUID id;
    public String text;
}
