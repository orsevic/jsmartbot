package com.jsmartbot.bot.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jsmartbot.bot.api.sevices.UserDataService;
import com.jsmartbot.bot.configurations.FreemarkerEngine;
import com.jsmartbot.bot.configurations.JavaScriptEngine;
import com.jsmartbot.bot.dao.AnswerDao;
import com.jsmartbot.bot.dao.PhraseDao;
import com.jsmartbot.bot.dao.PhraseRoadmapDao;
import com.jsmartbot.bot.entities.Phrase;
import com.jsmartbot.bot.entities.PhraseRoadmap;
import com.jsmartbot.bot.entities.PhraseType;
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
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private AnswerDao answerDao;

    public Optional<Phrase> getNextPhrase(UUID userId, UUID currentPhraseId, UUID answerId, String anotherAnswer) {
        Optional<PhraseRoadmap> roadmap = phraseRoadmapDao.findByPhraseId(currentPhraseId);

        if (roadmap.isPresent()) {
            String userProperty = roadmap.get().getUserProperty();
            String answerValue = answerId != null ? answerDao.findById(answerId).orElseThrow(() -> new IllegalArgumentException("Can not find this answer")).getText() : anotherAnswer;
            if (!StringUtils.isEmpty(userProperty)) {
                userDataService.set(userId, null, userProperty, answerValue);
            }
        }
        return roadmap
                .flatMap(value -> calculateNextPhrase(userId, answerId, anotherAnswer, value.getNextPhraseSupplier()))
                .map(phraseId -> phraseDao.getOne(phraseId))
                .map(phrase -> preparePhrase(phrase, userId));
    }

    public Optional<Phrase> getFirstPhrase(UUID userId) {
        return phraseDao.findOneByStartTrue().map(phrase -> preparePhrase(phrase, userId));
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

    private Phrase preparePhrase(Phrase phrase, UUID userId) {
        if (!phrase.getParamsSupplier().isPresent() || StringUtils.isEmpty(phrase.getParamsSupplier().get())) {
            return phrase.withPreparedText(phrase.getText());
        }

        try {
            Map<String, Object> params = prepareParams(userId, phrase.getParamsSupplier().get());
            String text = freemarkerEngine.process(phrase.getText(), Collections.singletonMap("params", params));
            phrase.withPreparedText(text);

            if (phrase.getType() == PhraseType.SELECTION && params.containsKey("users")) {
                phrase.withSelectedUses(((List<String>)params.get("users")).stream().map(element -> UUID.fromString(element)).collect(Collectors.toList()));
            }
        } catch (Exception ex) {
            logger.error("Error while prepare template - {}", ex.getMessage(), ex);
        }
        return phrase;
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
        if (answerId != null) {
            context.put("answer", answerDao.findById(answerId).get());
        }
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
