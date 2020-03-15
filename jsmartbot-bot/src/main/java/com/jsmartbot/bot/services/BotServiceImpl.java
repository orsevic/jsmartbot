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
    private AdminApiService adminApiService;
    @Autowired
    private UserStateDao userStateDao;
    @Autowired
    private PhraseDao phraseDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private PhraseRoadmapService phraseRoadmapService;
    @Autowired
    private AuthService authService;

    @Override
    public PhraseDto answerQuestion(String userId, UUID answerId, String anotherAnswer) {
        UserDto user = authService.findOrCreateUser(userId);
        Optional<UserState> userState = userStateDao.findById(user.getId().toString());
        Optional<Phrase> nextPhrase;

        if (!userState.isPresent()) {
            nextPhrase = phraseRoadmapService.getFirstPhrase(user.getId());
            userState = Optional.of(new UserState(user.getId().toString(), nextPhrase.get().getId()));
        } else {
            nextPhrase = phraseRoadmapService.getNextPhrase(user.getId(), userState.get().getCurrentPhraseId(), answerId, anotherAnswer);
            if (nextPhrase.isPresent()) {
                userState.get().setCurrentPhraseId(nextPhrase.get().getId());
            }
        }

        userStateDao.save(userState.get());
        return nextPhrase.map(entity -> new PhraseDto(entity.getId(), entity.getPreparedText(),
                answerDao.findByPhraseId(entity.getId()).stream().map(answer -> new AnswerDto(answer.getId(), answer.getText()))
                        .collect(Collectors.toList())))
                .orElse(new PhraseDto(UUID.randomUUID(), "We do not have question anymore", Collections.emptyList()));
    }




}
