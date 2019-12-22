package com.jsmartbot.bot.services;


import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jsmartbot.bot.api.dto.AnswerDto;
import com.jsmartbot.bot.api.dto.QuestionDto;
import com.jsmartbot.bot.api.sevices.BotService;
import com.jsmartbot.bot.dao.AnswerDao;
import com.jsmartbot.bot.dao.QuestionDao;
import com.jsmartbot.bot.dao.UserStateDao;
import com.jsmartbot.bot.entities.Question;
import com.jsmartbot.bot.entities.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sergeyorlov
 **/
@Transactional
@Service
public class BotServiceImpl implements BotService {
    @Autowired
    private AdminApiService adminApiService;
    @Autowired
    private UserStateDao userStateDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionRoadmapService questionRoadmapService;

    @Override
    public QuestionDto answerQuestion(String userId, UUID answerId, String anotherAnswer) {

        Optional<UserState> userState = userStateDao.findById(userId);
        Optional<Question> nextQuestion = Optional.empty();
        if (!userState.isPresent()) {
            nextQuestion = questionRoadmapService.getFirstQuestion();
            userState = Optional.of(new UserState(userId, nextQuestion.get().getId()));
        } else if (answerId != null || anotherAnswer != null) {
            nextQuestion = questionRoadmapService.getNextQuestion(
                    userState.get().getCurrentQuestionId(), answerId, anotherAnswer);
            userState.get().setCurrentQuestionId(nextQuestion.get().getId());
        } else {
            nextQuestion = questionDao.findById(userState.get().getCurrentQuestionId());
        }
        userStateDao.save(userState.get());
        return nextQuestion.map(entity -> new QuestionDto(entity.getId(), entity.getText(),
                answerDao.findByQuestionId(entity.getId()).stream().map(answer -> new AnswerDto(answer.getId(), answer.getText()))
                        .collect(Collectors.toList())))
                .orElse(new QuestionDto(UUID.randomUUID(), "We not have question anymore", Collections.emptyList()));
    }


}
