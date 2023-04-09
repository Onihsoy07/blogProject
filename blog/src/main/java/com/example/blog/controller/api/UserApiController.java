package com.example.blog.controller.api;

import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Role;
import com.example.blog.entity.Users;
import com.example.blog.service.UsersService;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UsersService usersService;


    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody Users users) {
        System.out.println("/api/user save 호출됨");
        users.setRole(Role.USER);
        int result = usersService.join(users);
        return new ResponseDto<>(HttpStatus.OK,result);
    }

    /*
    //기본 로그인 연습
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> longin(@RequestBody Users users, HttpSession session) {
        System.out.println("/blog/api/user/login login 호출됨");
        Users principal = usersService.longin(users);

        if (principal != null) {
            session.setAttribute("principal", principal);
            return new ResponseDto<>(HttpStatus.OK,1);
        } else {
            System.out.println("로그인 error");
            throw new IllegalArgumentException("로그인 error");
//            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR,-1);
        }
    }
     */

    

}
