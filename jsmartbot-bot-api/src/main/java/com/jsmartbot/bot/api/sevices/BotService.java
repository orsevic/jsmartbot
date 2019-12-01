package com.jsmartbot.bot.api.sevices;


import com.jsmartbot.bot.api.dto.QuestionDto;

import java.util.UUID;

/**
 * @author sergeyorlov
 **/
public interface BotService {
    QuestionDto answerQuestion(String userId, UUID answerId, String textAnswer);
}
