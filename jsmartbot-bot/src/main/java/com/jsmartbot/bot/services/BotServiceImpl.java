package com.jsmartbot.bot.services;


import com.jsmartbot.bot.api.dto.QuestionDto;
import com.jsmartbot.bot.api.sevices.BotService;
import com.jsmartbot.bot.dao.QuestionDao;
import com.jsmartbot.bot.dao.UserStateDao;
import com.jsmartbot.bot.entities.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

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
    private QuestionDao questionDao;

    @Override
    public QuestionDto answerQuestion(String userId, UUID answerId, String anotherAnswer) {

        Optional<UserState> userState = userStateDao.findById(userId);

//        questionDao.findOneByAnswerId(answerId);
//
//        if (!userState.isPresent()) {
//            userState
//        }

        return adminApiService.list().stream()
                .filter(question -> !question.getAnswers().isEmpty()).findFirst()
                .orElse(new QuestionDto(UUID.randomUUID(), "We not have question anymore", Collections.emptyList()));
    }
}
