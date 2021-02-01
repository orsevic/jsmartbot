package com.jsmartbot.bot.services;


import java.util.Collections;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jsmartbot.auth.api.dto.UserDto;
import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.dto.AnswerDto;
import com.jsmartbot.bot.api.dto.PhraseDto;
import com.jsmartbot.bot.api.sevices.BotService;
import com.jsmartbot.bot.dao.AnswerDao;
import com.jsmartbot.bot.dao.PhraseDao;
import com.jsmartbot.bot.dao.UserStateDao;
import com.jsmartbot.bot.entities.Phrase;
import com.jsmartbot.bot.entities.PhraseType;
import com.jsmartbot.bot.entities.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sergeyorlov
 **/
@Transactional
@Service
public class BotServiceImpl implements BotService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserStateDao userStateDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private PhraseRoadmapService phraseRoadmapService;

    @Override
    public PhraseDto answerQuestion(UUID userId, UUID answerId, String anotherAnswer) {

        Optional<UserState> userState = userStateDao.findById(userId);
        Optional<Phrase> nextPhrase;

        if (!userState.isPresent()) {
            nextPhrase = phraseRoadmapService.getFirstPhrase(userId);
            userState = Optional.of(new UserState(userId, nextPhrase.get().getId()));
        } else {
            nextPhrase = phraseRoadmapService.getNextPhrase(userId, userState.get().getCurrentPhraseId(), answerId, anotherAnswer);
            if (nextPhrase.isPresent()) {
                userState.get().setCurrentPhraseId(nextPhrase.get().getId());
            }
        }

        userStateDao.save(userState.get());
        PhraseDto result = nextPhrase.map(entity -> new PhraseDto(entity.getId(), entity.getPreparedText(),
                entity.getType().name(),
                answerDao.findByPhraseId(entity.getId()).stream().map(answer -> new AnswerDto(answer.getId(),
                        answer.getText()))
                        .collect(Collectors.toList()), entity.getSelectedUsers()))
                .orElse(new PhraseDto(UUID.randomUUID(), "We do not have question anymore", PhraseType.PHRASE.name(),
                        Collections.emptyList(), Collections.emptyList()));

        return result;
    }




}
