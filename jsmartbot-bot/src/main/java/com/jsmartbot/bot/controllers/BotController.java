package com.jsmartbot.bot.controllers;

import java.util.List;

import com.jsmartbot.bot.api.dto.AnswerQuestionDto;
import com.jsmartbot.bot.api.dto.QuestionDto;
import com.jsmartbot.bot.api.sevices.BotService;
import com.jsmartbot.bot.services.AdminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "bot-api")
public class BotController {
    @Autowired
    private BotService botService;

    @PostMapping(value = "answer-question")
    public QuestionDto answerQuestion (@RequestBody AnswerQuestionDto request) {
        return botService.answerQuestion(request.getUserId(), request.getAnswerId(), request.getAnotherAnswer());
    }
}
