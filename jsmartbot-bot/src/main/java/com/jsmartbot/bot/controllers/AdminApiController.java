package com.jsmartbot.bot.controllers;

import com.jsmartbot.bot.controllers.dto.Question;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminApiController {
    private List<Question> questions = new ArrayList<>();

    @PutMapping(value = "/add")
    public List<Question> add (@RequestBody Question element) {
        questions.add(element);
        return questions;
    }

    @DeleteMapping(value = "/delete")
    public List<Question> delete (@RequestBody Question element) {
        questions.remove(element);
        return questions;
    }

    @GetMapping(value = "/list")
    public List<Question> list () {
        return questions;
    }
}
