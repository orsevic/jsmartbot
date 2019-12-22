package com.jsmartbot.bot.services;


import com.jsmartbot.bot.api.dto.QuestionDto;
import com.jsmartbot.bot.api.sevices.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

/**
 * @author sergeyorlov
 **/
@Service
public class BotServiceImpl implements BotService {
    @Autowired
    private AdminApiService adminApiService;
    @Override
    public QuestionDto answerQuestion(String userId, UUID answerId, String anotherAnswer) {

        return adminApiService.list().stream()
                .filter(question -> !question.getAnswers().isEmpty()).findFirst()
                .orElse(new QuestionDto(UUID.randomUUID(), "We not have question anymore", Collections.emptyList()));
    }
}
