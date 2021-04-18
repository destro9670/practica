package com.example.demo.controllers.auth;

import com.example.demo.dto.SingUp;
import com.example.demo.models.User;
import com.example.demo.service.UserService;
import com.example.demo.validators.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String singin(Model model) {
        model.addAttribute("isLogined", "false");
        return "auth/singin";
    }

    @GetMapping("/")
    public String redirect(Model model, HttpServletRequest request) {
        return "redirect:/zdu";
    }

    @GetMapping("/logout")
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        model.addAttribute("isLogined", "false");

        return "redirect:/zdu";
    }

    @GetMapping("/singup")
    public String singUp(Model model) {

        model.addAttribute("isLogined", "false");


        return "auth/singup";
    }

    @PostMapping("/singup/add-user")
    public String singUpProcess(@ModelAttribute SingUp singUpData, Model model) {


        switch (PasswordValidator.validate(singUpData.getConfirmPassword(), singUpData.getPassword())) {
            case 0:

                Optional<User> optionalUser = Optional.ofNullable(userService.findByLogin(singUpData.getLogin()));

                if (!optionalUser.isPresent()) {
                    User user = new User();
                    user.setLogin(singUpData.getLogin());
                    user.setPassword(singUpData.getPassword());
                    userService.save(user);
                    return "redirect:/login";
                } else {
                    ///TODO(1) user exist
                    return "redirect:/singup";
                }

            case 1:
                ///TODO(0) catch password exeption;
                return "redirect:/singup";
            case 2:
                ///TODO(0) catch password exeption;
                return "redirect:/singup";
            default:
                return "redirect:/singup";
        }

    }
}