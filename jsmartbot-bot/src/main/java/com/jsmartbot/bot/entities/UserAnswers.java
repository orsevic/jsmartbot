package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class UserAnswers {
    @Id
    private UUID id;
    private UUID questionId;

    private String text;
}
