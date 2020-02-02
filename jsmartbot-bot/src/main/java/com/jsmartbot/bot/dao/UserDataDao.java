package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataDao extends JpaRepository<UserData, String> {
}
