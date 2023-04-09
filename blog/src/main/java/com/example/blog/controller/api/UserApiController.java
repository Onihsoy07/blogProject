package com.example.blog.controller.api;

import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Role;
import com.example.blog.entity.Users;
import com.example.blog.service.UsersService;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UsersService usersService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody Users users) {
        System.out.println("/api/user save 호출됨");
        users.setRole(Role.USER);
        int result = usersService.join(users);
        return new ResponseDto<>(HttpStatus.OK,result);
    }



}
