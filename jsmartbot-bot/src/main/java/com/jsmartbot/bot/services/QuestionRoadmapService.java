package com.jsmartbot.bot.services;

import java.util.Optional;
import java.util.UUID;

import com.jsmartbot.bot.dao.QuestionDao;
import com.jsmartbot.bot.dao.QuestionRoadmapDao;
import com.jsmartbot.bot.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sergeyorlov
 **/
@Service
public class QuestionRoadmapService {
    @Autowired
    public QuestionDao questionDao;
    public Optional<Question> getNextQuestion(UUID currentQuestionId, UUID answerId, String anotherAnswer) {
        if(answerId != null) {
            return questionDao.findOneByQuestionIdAndAnswerId(currentQuestionId, answerId);
        } else if (anotherAnswer != null) {
            return questionDao.findOneByQuestionIdAndAnswerText(currentQuestionId, anotherAnswer);
        }
        return Optional.empty();
    }

    public Optional<Question> getFirstQuestion() {
        return questionDao.findOneByStartTrue();
    }
}
