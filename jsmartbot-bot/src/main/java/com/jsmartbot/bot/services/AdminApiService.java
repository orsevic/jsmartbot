package com.jsmartbot.bot.services;

import com.jsmartbot.bot.api.dto.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminApiService {
    private List<Question> questions = new ArrayList<>();

    public List<Question> add (Question element) {
        questions.add(element);
        return questions;
    }

    public List<Question> delete (Question element) {
        questions.remove(element);
        return questions;
    }

    public List<Question> list () {
        return questions;
    }
}
