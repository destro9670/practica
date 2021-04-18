package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SingUp {

    private String login;
    private String password;
    private String confirmPassword;

}