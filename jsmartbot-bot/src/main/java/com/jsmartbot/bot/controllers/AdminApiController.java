package com.jsmartbot.bot.controllers;

import com.jsmartbot.bot.api.dto.QuestionDto;
import com.jsmartbot.bot.services.AdminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "admin-api")
public class AdminApiController {

    @Autowired
    private AdminApiService adminApiService;

    @PutMapping(value = "add")
    public List<QuestionDto> add (@RequestBody QuestionDto element) {
        return adminApiService.add(element);
    }

    @DeleteMapping(value = "delete")
    public List<QuestionDto> delete (@RequestBody QuestionDto element) {
        return adminApiService.delete(element);
    }

    @GetMapping(value = "list")
    public List<QuestionDto> list () {
        return adminApiService.list();
    }
}
