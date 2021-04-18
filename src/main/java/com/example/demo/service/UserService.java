package com.example.demo.service;

import com.example.demo.models.User;

import java.util.List;

public interface UserService {

    void save(User user);

    void update(User user);

    User findByLogin(String login);

    List<User> findAll();
}
