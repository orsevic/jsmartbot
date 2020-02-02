package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.UserProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPropertyDao extends JpaRepository<UserProperty, String> {
}
