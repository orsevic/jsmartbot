package com.jsmartbot.bot.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jsmartbot.bot.entities.UserProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserPropertyDao extends JpaRepository<UserProperty, String> {
    Optional<UserProperty> findOneByNameAndParentId(@Param("name") String name, @Param("parentId") UUID parentId);
}
