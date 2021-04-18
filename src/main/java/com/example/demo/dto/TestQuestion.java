package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestQuestion {

    private String testName;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private boolean ans1IsTrue;
    private boolean ans2IsTrue;
    private boolean ans3IsTrue;
    private boolean ans4IsTrue;

    @Override
    public String toString() {
        return "TestQuestion{" +
                "testName='" + testName + '\'' +
                ", question='" + question + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", ans1IsTrue=" + ans1IsTrue +
                ", ans2IsTrue=" + ans2IsTrue +
                ", ans3IsTrue=" + ans3IsTrue +
                ", ans4IsTrue=" + ans4IsTrue +
                '}';
    }
}
