package com.jsmartbot.bot.dao;

import com.jsmartbot.bot.entities.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PhraseDao extends JpaRepository<Phrase, UUID> {
    @Query(value = "select * from phrase q inner join answer a ON q.id = a.phrase_id  where a.id = :answerId", nativeQuery = true)
    public Phrase getOneByAnswerId(@Param("answerId") UUID answerId);

    @Query(value = "select * from phrase q inner join phrase_roadmap qr ON q.id = qr.next_phrase_supplier  where qr.phrase_id = :phraseId AND qr.answer_id = :answerId", nativeQuery = true)
    Optional<Phrase> findOneByPhraseIdAndAnswerId(@Param("phraseId") UUID currentPhraseId, @Param("answerId") UUID answerId);

    @Query(value = "select * from phrase q inner join phrase_roadmap qr ON q.id = qr.next_phrase_supplier  where qr.phrase_id = :phraseId AND qr.answer_text = :answerText", nativeQuery = true)
    Optional<Phrase> findOneByPhraseIdAndAnswerText(@Param("phraseId") UUID currentPhraseId, @Param("answerText") String anotherAnswer);

    @Query(value = "select * from phrase q inner join phrase_roadmap qr ON q.id = qr.phrase_id  where qr.start = true", nativeQuery = true)
    Optional<Phrase> findOneByStartTrue();
}
