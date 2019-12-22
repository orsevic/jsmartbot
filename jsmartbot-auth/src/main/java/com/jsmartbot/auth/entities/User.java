package com.jsmartbot.auth.entities;

import jdk.vm.ci.meta.Local;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String telegramId;
    private LocalDateTime lastEntry;

    public User() {
    }

    public User(String telegramId) {
        this.telegramId = telegramId;
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
