package com.example.demo.dto;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Test {

    private List<String> answers;
    private String question;
}
