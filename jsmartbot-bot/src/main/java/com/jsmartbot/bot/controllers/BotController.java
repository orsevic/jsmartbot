package com.jsmartbot.bot.controllers;

import java.util.List;

import com.jsmartbot.bot.api.dto.AnswerQuestionDto;
import com.jsmartbot.bot.api.dto.QuestionDto;
import com.jsmartbot.bot.api.sevices.BotService;
import com.jsmartbot.bot.services.AdminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {
    @Autowired
    private BotService botService;

    @PostMapping(value = "answerQuestion")
    public QuestionDto answerQuestion (@RequestBody AnswerQuestionDto request) {
        return botService.answerQuestion(request.getUserId(), request.getAnswerId(), request.getAnotherAnswer());
    }
}
