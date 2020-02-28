package com.jsmartbot.bot.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jsmartbot.bot.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDataDao extends JpaRepository<UserData, String> {
    Optional<UserData> findOneByUserIdAndPropertyIdAndParentId(@Param("userId") UUID userId, @Param("propertyId") UUID id, @Param("parentId") UUID groupId);

    @Query(nativeQuery = true, value =
            "SELECT cast(ud.user_id as varchar(32)) FROM user_data ud WHERE ud.property_id = :propertyId AND ud.value = :value")
    List<UUID> findUsersByPropertyValue(@Param("propertyId") UUID propertyId, @Param("value")  String value);
}
