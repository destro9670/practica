package com.example.demo.controllers.user;

import com.example.demo.dto.Test;
import com.example.demo.dto.TestAnswer;
import com.example.demo.models.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/zdu/user")
public class UserController {

    private final UserService userService;
    private final MarkService markService;
    private final ThemeService themeService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public UserController(UserService userService, MarkService markService, ThemeService themeService, QuestionService questionService, AnswerService answerService) {
        this.userService = userService;
        this.markService = markService;
        this.themeService = themeService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/mark_list")
    public String pupils(Model model, HttpServletRequest request){
        model.addAttribute("role", "user");
        model.addAttribute("isLogined", "true");
        model.addAttribute("module", "mark_list");

        User user = userService.findByLogin(request.getUserPrincipal().getName());

        List<Mark> marks= markService.findByUser(user);

        if(marks.isEmpty() || marks.size() == 0 ){
            model.addAttribute("no_marks", true);
            return "user/mark_list";
        }
            model.addAttribute("no_marks", false);

        int i =0;
        Mark[] marksArr = new Mark[marks.size()];
        for (Mark m: marks ) {
            marksArr[i] = m;
            i++;
        }
        model.addAttribute("marks", marksArr);


        return "user/mark_list";
    }

    @GetMapping("/test")
    public String getTest( Model model){
        model.addAttribute("role", "user");
        model.addAttribute("isLogined", "true");
        model.addAttribute("module", "test");

        List<Question> questions = new ArrayList<>();

        Theme theme = themeService.findById(1);
        questions = questionService.findByTheme(theme);

        List<Test> tests = new LinkedList<>();

        for (int i = 0; i <questions.size() ; i++) {
            Test test = new Test();
            test.setQuestion(questions.get(i).getName());
            Question tmp = questions.get(i);
            test.setAnswers(answerService.findByQuestion(tmp).stream().map(Answer::getName).collect(Collectors.toList()));
            tests.add(test);
        }

        if(tests.isEmpty())
            model.addAttribute("isEmpty",true);
        else {
            model.addAttribute("tests", tests);
            model.addAttribute("hideButton", false);
            model.addAttribute("isEmpty", false);
        }

        return "user/test";
    }

    @PostMapping("/test")
    public String handleTestResult(@ModelAttribute TestAnswer testAnswer, Model model, HttpServletRequest request){
        model.addAttribute("role", "user");
        model.addAttribute("isLogined", "true");
        model.addAttribute("module", "test");

        int mark = 0;
        for (String ans: testAnswer.getOpt()) {
            if (answerService.findByName(ans).isCorrect())
                mark++;
        }

        model.addAttribute("mark",mark);
        model.addAttribute("hideButton",true);
        model.addAttribute("isEmpty",false);

        User user = userService.findByLogin(request.getUserPrincipal().getName());
        Mark mark1 = new Mark();
        mark1.setUser(user);
        mark1.setCount(mark);
        Date date = new Date();
        mark1.setReceipt(date);
        markService.save(mark1);

        return "user/test";
    }

}
