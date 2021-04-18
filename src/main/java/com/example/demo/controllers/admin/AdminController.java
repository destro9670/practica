package com.example.demo.controllers.admin;

import com.example.demo.dto.TestQuestion;
import com.example.demo.dto.UserHasMark;
import com.example.demo.models.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/zdu/admin")
public class AdminController {

    private final UserService userService;
    private final MarkService markService;
    private final ThemeService themeService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public AdminController(UserService userService, MarkService markService, ThemeService themeService, QuestionService questionService, AnswerService answerService) {
        this.userService = userService;
        this.markService = markService;
        this.themeService = themeService;
        this.questionService = questionService;
        this.answerService = answerService;
    }


    @GetMapping("/pupil_list")
    public String pupilList(Model model){
        model.addAttribute("role", "admin");
        model.addAttribute("isLogined", "true");
        model.addAttribute("module", "pupil_list");

        List<User> users = userService.findAll();

        UserHasMark tmp = new UserHasMark();
        List<UserHasMark> res = new ArrayList<>();
        for (User user : users) {
                if (user.getLogin().equals("admin"))
                    continue;
                tmp.setFulName(user.getLogin());
                List<Mark> marks = markService.findByUser(user);
                List<Date> tmpDates = new ArrayList<>();
                List<Integer> tmpMarks = new ArrayList<>();
                for (Mark m : marks) {
                    tmpDates.add(m.getReceipt());
                    tmpMarks.add(m.getCount());
                }
                tmp.setDates(tmpDates);
                tmp.setMarks(tmpMarks);
                res.add(tmp);
                tmp=new UserHasMark();
        }

        if(res.isEmpty()){
            model.addAttribute("no_marks", true);
            return "admin/pupil_list";
        }
        model.addAttribute("no_marks", false);

        model.addAttribute("marks", res);
        return "admin/pupil_list";
    }

    @GetMapping("/test_manager")
    public String testManager(Model model){

        model.addAttribute("role", "admin");
        model.addAttribute("isLogined", "true");
        model.addAttribute("module", "test_manager");

        return "admin/test_manager";
    }

    @PostMapping("/test_manager")
    public String getTestQuestion(@ModelAttribute TestQuestion question, Model model){
        System.out.println(question.toString());

        Theme theme = themeService.findByName(question.getTestName());
        if (theme == null){
            theme = new Theme();
            theme.setName(question.getTestName());
            theme = themeService.save(theme);
        }
        theme.setQuestionrs(questionService.findByTheme(theme));

        if (theme.getQuestionrs().size() >= 12){
            model.addAttribute("full","<div class=\"form-group py-2 row alert alert-danger alert-dismissible fade show\">\n" +
                    "        <strong>Помилка!</strong> Досягнуто ліміт тестів по даному модулю.\n" +
                    "        <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\n" +
                    "    </div>");
            return "admin/test_manager";
        }

        Question quest = new Question();
        quest.setName(question.getQuestion());
        quest.setTheme(theme);
        quest = questionService.save(quest);


        Answer answer1 = new Answer();
        answer1.setName(question.getAnswer1());
        answer1.setCorrect(question.isAns1IsTrue());
        answer1.setQuestion(quest);
        answerService.save(answer1);

        Answer answer2 = new Answer();
        answer2.setName(question.getAnswer2());
        answer2.setCorrect(question.isAns2IsTrue());
        answer2.setQuestion(quest);
        answerService.save(answer2);

        Answer answer3 = new Answer();
        answer3.setName(question.getAnswer3());
        answer3.setCorrect(question.isAns3IsTrue());
        answer3.setQuestion(quest);
        answerService.save(answer3);

        Answer answer4 = new Answer();
        answer4.setName(question.getAnswer4());
        answer4.setCorrect(question.isAns4IsTrue());
        answer4.setQuestion(quest);
        answerService.save(answer4);

        model.addAttribute("lastTheme", question.getTestName());
        model.addAttribute("role", "admin");
        model.addAttribute("isLogined", "true");
        model.addAttribute("module", "test_manager");
        return "admin/test_manager";
    }
}
