package com.example.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @GetMapping("/test/hello")
    public String hello() {
        return "<h1 style=\"color:red\">hello spring boot</h1><ol><dl>aaa</dl><dl>bbb</dl></ol>";
    }

}
