package com.example.demo.repositories;

import com.example.demo.models.Mark;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    List<Mark> findByUser(User user);
    Mark save(Mark mark);
}
