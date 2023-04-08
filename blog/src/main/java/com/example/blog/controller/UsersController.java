package com.example.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsersController {

    @GetMapping("user/joinForm")
    public String joinFrom() {

        return "user/joinForm";
    }

    @GetMapping("user/loginForm")
    public String loginForm() {

        return "user/loginForm";
    }

}