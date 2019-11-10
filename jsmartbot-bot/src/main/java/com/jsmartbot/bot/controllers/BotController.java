package com.jsmartbot.bot.controllers;

import com.jsmartbot.bot.services.AdminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {
    @Autowired
    private AdminApiService adminApiService;
}
