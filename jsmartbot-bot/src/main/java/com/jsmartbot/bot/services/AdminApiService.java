package com.jsmartbot.bot.services;

import com.jsmartbot.bot.api.dto.AnswerDto;
import com.jsmartbot.bot.api.dto.PhraseDto;
import com.jsmartbot.bot.dao.AnswerDao;
import com.jsmartbot.bot.dao.PhraseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminApiService {
    @Autowired
    private PhraseDao questionDao;
    @Autowired
    private AnswerDao answerDao;
    private List<PhraseDto> questions = new ArrayList<>();

    public List<PhraseDto> add (PhraseDto element) {
        questions.add(element);
        return questions;
    }

    public List<PhraseDto> delete (PhraseDto element) {
        questions.remove(element);
        return questions;
    }

    public List<PhraseDto> list () {
        return questionDao.findAll().stream().map(
                entity -> new PhraseDto(entity.getId(), entity.getText(),
                        answerDao.findByPhraseId(entity.getId()).stream().map(answer -> new AnswerDto(answer.getId(), answer.getText()))
                                .collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
}
