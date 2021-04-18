package com.example.demo.service.impl;

import com.example.demo.models.Mark;
import com.example.demo.models.User;
import com.example.demo.repositories.MarkRepository;
import com.example.demo.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public List<Mark> findByUser(User user) {
        return markRepository.findByUser(user);
    }

    @Override
    public Mark save(Mark mark) {
        return markRepository.save(mark);
    }
}
