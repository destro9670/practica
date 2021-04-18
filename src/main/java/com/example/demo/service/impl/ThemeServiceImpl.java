package com.example.demo.service.impl;

import com.example.demo.models.Theme;
import com.example.demo.repositories.ThemeRepository;
import com.example.demo.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;
    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public Theme save(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Theme findByName(String name) {
        return themeRepository.findByName(name);
    }

    @Override
    public Theme findById(long id) {
        return themeRepository.findById(id);
    }
}
