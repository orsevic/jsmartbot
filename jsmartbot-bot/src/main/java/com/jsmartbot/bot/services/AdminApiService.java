package com.jsmartbot.bot.services;

import com.jsmartbot.bot.api.dto.AnswerDto;
import com.jsmartbot.bot.api.dto.QuestionDto;
import com.jsmartbot.bot.dao.AnswerDao;
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
    @Autowired
    private AnswerDao answerDao;
    private List<QuestionDto> questions = new ArrayList<>();

    public List<QuestionDto> add (QuestionDto element) {
        questions.add(element);
        return questions;
    }

    public List<QuestionDto> delete (QuestionDto element) {
        questions.remove(element);
        return questions;
    }

    public List<QuestionDto> list () {
        return questionDao.findAll().stream().map(
                entity -> new QuestionDto(entity.getId(), entity.getText(),
                        answerDao.findByQuestionId(entity.getId()).stream().map(answer -> new AnswerDto(answer.getId(), answer.getText()))
                                .collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
}
