package com.example.blog.controller.api;

import com.example.blog.entity.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public int save(@RequestBody Users users) {
        System.out.println("/api/user save 요청됨");
        return 1;
    }

}
