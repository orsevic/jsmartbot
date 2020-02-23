package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AnswerDao extends JpaRepository<Answer, UUID> {
    List<Answer> findByPhraseId(@Param("phraseId")UUID phraseId);
}
