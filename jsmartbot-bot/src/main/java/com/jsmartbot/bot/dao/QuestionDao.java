package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.rmi.server.UID;
import java.util.UUID;

public interface QuestionDao extends JpaRepository<Question, UID> {
    @Query(value = "select * from question q inner join answer a ON q.id = a.quetion_id  where a.id = :answerId", nativeQuery = true)
    public Question getOneByAnswerId(@Param("answerId") UUID answerId);
}
