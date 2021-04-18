package com.example.demo.service;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;

import java.util.List;

public interface AnswerService {
    Answer save(Answer answer);

    List<Answer> findByQuestion(Question question);

    Answer findByName(String ans);
}
