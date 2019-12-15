package com.jsmartbot.auth.dao;


import com.jsmartbot.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserDao extends JpaRepository<User, UUID> {
    Optional<User> findOneByTelegramId(@Param("telegramId") String telegramId);

}
