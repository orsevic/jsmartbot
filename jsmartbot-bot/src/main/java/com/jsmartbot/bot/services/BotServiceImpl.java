package com.jsmartbot.bot.services;


import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jsmartbot.auth.api.dto.UserDto;
import com.jsmartbot.auth.api.services.AuthService;
import com.jsmartbot.bot.api.dto.AnswerDto;
import com.jsmartbot.bot.api.dto.QuestionDto;
import com.jsmartbot.bot.api.sevices.BotService;
import com.jsmartbot.bot.configurations.FreemarkerEngine;
import com.jsmartbot.bot.configurations.JavaScriptEngine;
import com.jsmartbot.bot.dao.AnswerDao;
import com.jsmartbot.bot.dao.QuestionDao;
import com.jsmartbot.bot.dao.UserStateDao;
import com.jsmartbot.bot.entities.Question;
import com.jsmartbot.bot.entities.UserState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private QuestionDao questionDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionRoadmapService questionRoadmapService;
    @Autowired
    private AuthService authService;
    @Autowired
    private FreemarkerEngine freemarkerEngine;
    @Autowired
    private JavaScriptEngine javaScriptEngine;

    @Override
    public QuestionDto answerQuestion(String userId, UUID answerId, String anotherAnswer) {
        UserDto user = authService.findOrCreateUser(userId);
        Optional<UserState> userState = userStateDao.findById(user.getId().toString());
        Optional<Question> nextQuestion = Optional.empty();
        if (!userState.isPresent()) {
            nextQuestion = questionRoadmapService.getFirstQuestion();
            userState = Optional.of(new UserState(user.getId().toString(), nextQuestion.get().getId()));
        } else if (answerId != null || anotherAnswer != null) {
            nextQuestion = questionRoadmapService.getNextQuestion(userState.get().getCurrentQuestionId(), answerId, anotherAnswer);
            if (nextQuestion.isPresent()) {
                userState.get().setCurrentQuestionId(nextQuestion.get().getId());
            }
        } else {
            nextQuestion = questionDao.findById(userState.get().getCurrentQuestionId());
        }
        userStateDao.save(userState.get());
        return nextQuestion.map(entity -> new QuestionDto(entity.getId(), prepareText(entity.getText(), entity.getParams()),
                answerDao.findByQuestionId(entity.getId()).stream().map(answer -> new AnswerDto(answer.getId(), answer.getText()))
                        .collect(Collectors.toList())))
                .orElse(new QuestionDto(UUID.randomUUID(), "We do not have question anymore", Collections.emptyList()));
    }

    private String prepareText(String text, String paramsSuplier) {
        if (StringUtils.isEmpty(paramsSuplier)) {
            return text;
        }
        try {
            Map<String, Object> params = javaScriptEngine.evalParams(paramsSuplier);
            return freemarkerEngine.process(text, Collections.singletonMap("params", params));
        } catch (Exception ex) {
            logger.error("Error while prepare template - {}", ex.getMessage(), ex);
        }

        return text;
    }


}
