package com.example.demo.service;

import com.example.demo.models.Theme;

public interface ThemeService {
    Theme save(Theme theme);

    Theme findByName(String name);
    Theme findById(long id);

}
