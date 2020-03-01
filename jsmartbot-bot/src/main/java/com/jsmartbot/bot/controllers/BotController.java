package com.jsmartbot.bot.controllers;

import com.jsmartbot.bot.api.dto.ReplyDto;
import com.jsmartbot.bot.api.dto.PhraseDto;
import com.jsmartbot.bot.api.sevices.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "bot-api")
public class BotController {
    @Autowired
    private BotService botService;

    @PostMapping(value = "answer-question")
    public PhraseDto answerQuestion (@RequestBody ReplyDto request) {
        return botService.answerQuestion(request.getUserId(), request.getAnswerId(), request.getAnotherAnswer());
    }
}
