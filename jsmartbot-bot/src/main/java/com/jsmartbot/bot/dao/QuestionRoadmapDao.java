package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.PhraseRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;

public interface QuestionRoadmapDao extends JpaRepository<PhraseRoadmap, UID> {

}
