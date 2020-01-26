package com.jsmartbot.bot.services;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.jsmartbot.bot.configurations.FreemarkerEngine;
import com.jsmartbot.bot.configurations.JavaScriptEngine;
import com.jsmartbot.bot.dao.PhraseDao;
import com.jsmartbot.bot.entities.Phrase;
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
    private FreemarkerEngine freemarkerEngine;
    @Autowired
    private JavaScriptEngine javaScriptEngine;

    public Optional<Phrase> getNextPhrase(UUID currentPhraseId, UUID answerId, String anotherAnswer) {
        Optional<Phrase> result = Optional.empty();
        if(answerId != null) {
            result = phraseDao.findOneByPhraseIdAndAnswerId(currentPhraseId, answerId);
        } else if (anotherAnswer != null) {
            result = phraseDao.findOneByPhraseIdAndAnswerText(currentPhraseId, anotherAnswer);
        }
        return result.map(phrase -> phrase.withPreparedText(prepareText(phrase.getText(), phrase.getParamsSupplier())));
    }

    public Optional<Phrase> getFirstPhrase() {
        return phraseDao.findOneByStartTrue().map(phrase -> phrase.withPreparedText(prepareText(phrase.getText(), phrase.getParamsSupplier())));
    }

    private String prepareText(String text, Optional<String> paramsSupplier) {
        if (!paramsSupplier.isPresent() || StringUtils.isEmpty(paramsSupplier.get())) {
            return text;
        }
        try {
            Map<String, Object> params = javaScriptEngine.evalParams(paramsSupplier.get());
            return freemarkerEngine.process(text, Collections.singletonMap("params", params));
        } catch (Exception ex) {
            logger.error("Error while prepare template - {}", ex.getMessage(), ex);
        }

        return text;
    }
}
