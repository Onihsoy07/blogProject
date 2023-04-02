package com.example.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @GetMapping("/test/h")
    public String hello() {
        return "<h1>hello spring boot</h1>";
    }

}
