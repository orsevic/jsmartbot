package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;

public interface QuestionDao extends JpaRepository<Question, UID> {
}
