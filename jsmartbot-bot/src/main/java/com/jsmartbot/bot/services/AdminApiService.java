package com.jsmartbot.bot.services;

import com.jsmartbot.bot.api.dto.Question;
import com.jsmartbot.bot.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminApiService {
    @Autowired
    private QuestionDao questionDao;
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
        return questionDao.findAll().stream().map(entity -> new Question(entity.text)).collect(Collectors.toList());
    }
}
