package com.example.blog.service;

import com.example.blog.dto.KakaoProfile;
import com.example.blog.dto.OAuthToken;
import com.example.blog.dto.UsersDto;
import com.example.blog.entity.Users;

public interface UsersService {

    int join(Users users);

    Boolean usernameDuplicationCheck(String username);

    UsersDto updateUser(Users users);

    void deleteUser(Long id);

    OAuthToken getKakaoToken(String code);

    KakaoProfile getKakaoProfile(String accessToken);

    String kakaoApiLogin(KakaoProfile kakaoProfile);

}
