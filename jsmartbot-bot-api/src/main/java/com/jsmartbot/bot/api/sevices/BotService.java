package com.jsmartbot.bot.api.sevices;


import com.jsmartbot.bot.api.dto.Question;

/**
 * @author sergeyorlov
 **/
public interface BotService {
    public Question answerQuestion(int questionId, int answerId, String anotherAnswer);
}
