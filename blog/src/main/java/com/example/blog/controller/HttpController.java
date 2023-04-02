package com.example.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/http")
public class HttpController {

    @GetMapping("/get")
    public String getHttp() {
        return "get요청";
    }

    @PostMapping("/post")
    public String postHttp() {
        return "post요청";
    }

    @PutMapping("/put")
    public String putHttp() {
        return "put요청";
    }

    @DeleteMapping("/delete")
    public String deleteHttp() {
        return "delete요청";
    }

}
