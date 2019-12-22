package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

public interface QuestionDao extends JpaRepository<Question, UID> {
    @Query(value = "select * from question q inner join answer a ON q.id = a.question_id  where a.id = :answerId", nativeQuery = true)
    public Question getOneByAnswerId(@Param("answerId") UUID answerId);

    @Query(value = "select * from question q inner join question_roadmap qr ON q.id = qr.question_id  where q.id = :questionId AND qr.answer_id = :answerId", nativeQuery = true)
    Optional<Question> findOneByQuestionIdAndAnswerId(@Param("questionId") UUID currentQuestionId, @Param("answerId") UUID answerId);

    @Query(value = "select * from question q inner join question_roadmap qr ON q.id = qr.question_id  where q.id = :questionId AND qr.answer_text = :answerText", nativeQuery = true)
    Optional<Question> findOneByQuestionIdAndAnswerText(@Param("questionId") UUID currentQuestionId, @Param("answerText") String anotherAnswer);

    @Query(value = "select * from question q inner join question_roadmap qr ON q.id = qr.question_id  where qr.start = true", nativeQuery = true)
    Optional<Question> findOneByStartTrue();
}
