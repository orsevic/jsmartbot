package com.jsmartbot.bot.controllers;

import com.jsmartbot.bot.controllers.dto.Question;
import com.jsmartbot.bot.services.AdminApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminApiController {

    @Autowired
    private AdminApiService adminApiService;

    @PutMapping(value = "/add")
    public List<Question> add (@RequestBody Question element) {
        return adminApiService.add(element);
    }

    @DeleteMapping(value = "/delete")
    public List<Question> delete (@RequestBody Question element) {
        return adminApiService.delete(element);
    }

    @GetMapping(value = "/list")
    public List<Question> list () {
        return adminApiService.list();
    }
}
