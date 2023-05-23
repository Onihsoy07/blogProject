package com.example.blog.controller;


import com.example.blog.dto.KakaoProfile;
import com.example.blog.dto.OAuthToken;
import com.example.blog.service.UsersService;
import com.example.blog.service.impl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersService;

    @GetMapping("/auth/joinForm")
    public String joinFrom() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/auth/loginFail")
    public String loginFail() {
        return "user/loginFailForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/user/userForm")
    public String userForm() {
        return "user/userForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {

        OAuthToken kakaoToken = usersService.getKakaoToken(code);
        KakaoProfile kakaoProfile = usersService.getKakaoProfile(kakaoToken.getAccess_token());
        usersService.kakaoApiLogin(kakaoProfile);
        
        //redirect 안하면 kakaoCallback url로 view만 구현됨
        return "redirect:/";
    }

}
