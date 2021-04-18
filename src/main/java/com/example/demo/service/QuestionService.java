package com.example.demo.service;

import com.example.demo.models.Question;
import com.example.demo.models.Theme;

import java.util.List;

public interface QuestionService {
    Question save(Question question);

    List<Question> findByTheme(Theme theme);

}
