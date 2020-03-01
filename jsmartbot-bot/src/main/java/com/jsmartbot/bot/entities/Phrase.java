package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.lang.Nullable;


@Entity
public class Phrase {


    @Id
    private UUID id;
    @Enumerated(value = EnumType.STRING)
    private PhraseType type;
    private String text;
    @Nullable
    private String paramsSupplier;
    @Transient
    private String preparedText;
    @Transient
    private List<UUID> selectedUsers;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Optional<String> getParamsSupplier() {
        return Optional.ofNullable(paramsSupplier);
    }

    public void setParamsSupplier(String paramsSupplier) {
        this.paramsSupplier = paramsSupplier;
    }

    public String getPreparedText() {
        return preparedText;
    }

    public void setPreparedText(String preparedText) {
        this.preparedText = preparedText;
    }

    public PhraseType getType() {
        return type;
    }

    public List<UUID> getSelectedUsers() {
        return selectedUsers;
    }

    public Phrase withPreparedText(String preparedText) {
        this.preparedText = preparedText;
        return this;
    }
    public Phrase withSelectedUses(List<UUID> selectedUsers) {
        this.selectedUsers = selectedUsers;
        return this;
    }
}
