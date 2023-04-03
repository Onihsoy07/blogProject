package com.example.blog.controller;

import com.example.blog.entity.Member;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/http")
public class HttpController {

    @GetMapping("/get")
    public String getHttp(Member member) { //http://localhost:8080/http/get?id=1&name=test&password=1234&email=test@test.com
        return String.format("get요청 : %d, %s, %s, %s", member.getId(), member.getName(), member.getPassword(), member.getEmail());
    }

    @PostMapping("/post")
    public String postHttp(@RequestBody final Member member) { //http://localhost:8080/http/post
        return String.format("post요청 : %d, %s, %s, %s", member.getId(), member.getName(), member.getPassword(), member.getEmail());
    }

    @PutMapping("/put")
    public String putHttp(@RequestBody final Member member) {
        return String.format("put요청 : %d, %s, %s, %s", member.getId(), member.getName(), member.getPassword(), member.getEmail());
    }

    @DeleteMapping("/delete")
    public String deleteHttp(@RequestBody final Member member) {
        return String.format("delete요청 : %d, %s, %s, %s", member.getId(), member.getName(), member.getPassword(), member.getEmail());
    }

}
