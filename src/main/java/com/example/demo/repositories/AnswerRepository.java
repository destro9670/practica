package com.example.demo.repositories;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer save(Answer answer);

    List<Answer> findByQuestion(Question question);
    Answer findByName(String ans);
}
