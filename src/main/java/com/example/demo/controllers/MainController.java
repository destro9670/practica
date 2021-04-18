package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/zdu")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String root(Model model, HttpServletRequest request){
        model.addAttribute("module", "main");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().map(role->
                new SimpleGrantedAuthority(role.getAuthority())
        ).collect(Collectors.toList()).get(0)
                .getAuthority().equals("ROLE_ANONYMOUS")) {
            model.addAttribute("isLogined", "false");
            return "user/user";
        }else {
            model.addAttribute("isLogined", "true");

            User user = userService.findByLogin(request.getUserPrincipal().getName());

            if (user.getRole().getName().equals("ROLE_ADMIN")) {
                model.addAttribute("role", "admin");
            } else {
                model.addAttribute("role", "user");
            }
        }
        return "user/user";
    }

    @GetMapping("/lesson_list")
    public String lessons(Model model, HttpServletRequest request){
        model.addAttribute("module", "lessons");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().map(role->
                new SimpleGrantedAuthority(role.getAuthority())
        ).collect(Collectors.toList()).get(0)
                .getAuthority().equals("ROLE_ANONYMOUS")) {
            model.addAttribute("isLogined", "false");
            return "user/lessons";
        }else {
            model.addAttribute("isLogined", "true");

            User user = userService.findByLogin(request.getUserPrincipal().getName());

            if (user.getRole().getName().equals("ROLE_ADMIN")) {
                model.addAttribute("role", "admin");
            } else {
                model.addAttribute("role", "user");
            }
        }
        return "user/lessons";
    }


}
