package com.jsmartbot.bot.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;

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

    public Phrase withPreparedText(String preparedText) {
        this.preparedText = preparedText;
        return this;
    }
}
