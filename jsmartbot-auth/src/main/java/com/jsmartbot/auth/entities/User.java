package com.jsmartbot.auth.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String telegramId;
    private String firstName;
    private String lastName;
    private String telegramUserName;
    private LocalDateTime lastEntry;

    public User() {
    }

    public User(String telegramId, String telegramUserName, String firstName, String lastName) {
        this.telegramId = telegramId;
        this.telegramUserName = telegramUserName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelegramUserName() {
        return telegramUserName;
    }

    public void setTelegramUserName(String telegramUserName) {
        this.telegramUserName = telegramUserName;
    }

    public LocalDateTime getLastEntry() {
        return lastEntry;
    }

    public void setLastEntry(LocalDateTime lastEntry) {
        this.lastEntry = lastEntry;
    }
    @PrePersist
    public void persist(){
        this.lastEntry = LocalDateTime.now();
    }
}
