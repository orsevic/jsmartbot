package com.jsmartbot.bot.api.sevices;


import com.jsmartbot.bot.api.dto.PhraseDto;

import java.util.UUID;

/**
 * @author sergeyorlov
 **/
public interface BotService {
    PhraseDto answerQuestion(String userId, UUID answerId, String textAnswer);
}
