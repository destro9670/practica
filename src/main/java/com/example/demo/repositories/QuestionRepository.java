package com.example.demo.repositories;

import com.example.demo.models.Question;
import com.example.demo.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question save(Question question);

    List<Question> findByTheme(Theme theme);
}
