package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.UserState;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserStateDao extends JpaRepository<UserState, String> {
}
