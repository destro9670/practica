package com.example.demo.repositories;

import com.example.demo.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Long> {
    Theme save(Theme theme);

    Theme findByName(String name);

    Theme findById(long id);
}
