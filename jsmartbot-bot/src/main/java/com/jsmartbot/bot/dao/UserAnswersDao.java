package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;

public interface UserAnswersDao extends JpaRepository<UserAnswer, UID> {
}
