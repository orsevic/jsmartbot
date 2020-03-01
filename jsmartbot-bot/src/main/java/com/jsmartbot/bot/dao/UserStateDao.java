package com.jsmartbot.bot.dao;

import java.util.UUID;

import com.jsmartbot.bot.entities.UserState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserStateDao extends JpaRepository<UserState, UUID> {
}

