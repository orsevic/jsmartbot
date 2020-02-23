package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.PhraseRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

public interface PhraseRoadmapDao extends JpaRepository<PhraseRoadmap, UID> {

    Optional<PhraseRoadmap> findByPhraseId(@Param("phraseId") UUID currentPhraseId);
}
