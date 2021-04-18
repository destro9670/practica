package com.example.demo.service;

import com.example.demo.models.Mark;
import com.example.demo.models.User;

import java.util.List;

public interface MarkService {
    List<Mark> findByUser(User user);

    Mark save(Mark mark);
}
