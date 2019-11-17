package com.jsmartbot.bot.services;


import com.jsmartbot.bot.api.dto.Question;
import com.jsmartbot.bot.api.sevices.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sergeyorlov
 **/
@Service
public class BotServiceImpl implements BotService {
    @Autowired
    private AdminApiService adminApiService;
    @Override
    public Question answerQuestion(int questionId, int answerId, String anotherAnswer) {

        return adminApiService.list().stream().findFirst().orElse(new Question("We not have question anymore"));
    }
}
