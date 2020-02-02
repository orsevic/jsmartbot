package com.jsmartbot.bot.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.jsmartbot.bot.configurations.FreemarkerEngine;
import com.jsmartbot.bot.configurations.JavaScriptEngine;
import com.jsmartbot.bot.dao.PhraseDao;
import com.jsmartbot.bot.dao.PhraseRoadmapDao;
import com.jsmartbot.bot.entities.Phrase;
import com.jsmartbot.bot.entities.PhraseRoadmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author sergeyorlov
 **/
@Service
public class PhraseRoadmapService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PhraseDao phraseDao;
    @Autowired
    private PhraseRoadmapDao phraseRoadmapDao;
    @Autowired
    private FreemarkerEngine freemarkerEngine;
    @Autowired
    private JavaScriptEngine javaScriptEngine;

    public Optional<Phrase> getNextPhrase(UUID userId, UUID currentPhraseId, UUID answerId, String anotherAnswer) {
        Optional<Phrase> result = Optional.empty();
//        if(answerId != null) {
//            result = phraseDao.findOneByPhraseIdAndAnswerId(currentPhraseId, answerId);
//        } else if (anotherAnswer != null) {
//            result = phraseDao.findOneByPhraseIdAndAnswerText(currentPhraseId, anotherAnswer);
//        }
        Optional<PhraseRoadmap> roadmap = phraseRoadmapDao.findByPhraseId(currentPhraseId);

        return roadmap
                .flatMap(value -> calculateNextPhrase(userId, answerId, anotherAnswer, value.getNextPhraseSupplier()))
                .map(phraseId -> phraseDao.getOne(phraseId))
                .map(phrase -> phrase.withPreparedText(prepareText(userId, phrase.getText(), phrase.getParamsSupplier())));
    }

    public Optional<Phrase> getFirstPhrase(UUID userId) {
        return phraseDao.findOneByStartTrue().map(phrase -> phrase.withPreparedText(prepareText(userId, phrase.getText(), phrase.getParamsSupplier())));
    }

    private String prepareText(UUID userId, String text, Optional<String> paramsSupplier) {
        if (!paramsSupplier.isPresent() || StringUtils.isEmpty(paramsSupplier.get())) {
            return text;
        }
        try {
            Map<String, Object> params = prepareParams(userId, paramsSupplier.get());
            return freemarkerEngine.process(text, Collections.singletonMap("params", params));
        } catch (Exception ex) {
            logger.error("Error while prepare template - {}", ex.getMessage(), ex);
        }

        return text;
    }

    public Map<String, Object> prepareParams(UUID userId, String paramsSupplier) {
        Object result = javaScriptEngine.evalJs(paramsSupplier, Collections.singletonMap("context", Collections.singletonMap("userId", userId)));
        if (result instanceof Map) {
            return (Map<String, Object>) result;
        } else {
            throw new RuntimeException(String.format("Result of java script is not map - %s", result));
        }
    }

    public Optional<UUID> calculateNextPhrase(UUID userId, UUID answerId, String anotherAnswer, String nextPhraseSupplier) {
        Map<String, Object> context = new HashMap<>();
        context.put("userId", userId);
        context.put("answerId", answerId);
        context.put("anotherAnswer", anotherAnswer);
        Object result = javaScriptEngine.evalJs(nextPhraseSupplier, Collections.singletonMap("context", context));
        if (result == null) {
            return Optional.empty();
        }
        if (result instanceof String) {
            return Optional.of(UUID.fromString((String) result));
        } else if (result instanceof UUID) {
                return Optional.of((UUID) result);
        } else {
            throw new RuntimeException(String.format("Result of java script is not map - %s", result));
        }
    }
}
