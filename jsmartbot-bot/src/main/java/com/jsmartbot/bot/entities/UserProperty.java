package com.jsmartbot.bot.entities;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity
public class UserProperty {
    @Id
    private UUID id;
    private String name;
    private String type;
    @Nullable
    private UUID parentId;

    public UserProperty() {
    }

    public UserProperty(UUID id, String name, String type, @Nullable UUID parentId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Optional<UUID> getParentId() {
        return Optional.ofNullable(parentId);
    }
}
