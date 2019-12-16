package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.Question;
import com.jsmartbot.bot.entities.QuestionRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRoadmapDao extends JpaRepository<QuestionRoadmap, UID> {
    Optional<Question> findOneByQuestionIdAndAnswerId(@Param("questionId") UUID currentQuestionId, @Param("answerId") UUID answerId);
    Optional<Question> findOneByQuestionIdAndAnswerText(@Param("questionId") UUID currentQuestionId, @Param("answerText") String anotherAnswer);

    Optional<Question> findOneByStartTrue();
}
