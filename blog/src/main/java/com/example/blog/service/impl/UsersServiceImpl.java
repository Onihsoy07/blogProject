package com.example.blog.service.impl;

import com.example.blog.controller.api.UserApiController;
import com.example.blog.dto.KakaoProfile;
import com.example.blog.dto.OAuthToken;
import com.example.blog.dto.UsersDto;
import com.example.blog.entity.Users;
import com.example.blog.mapping.UsersMapping;
import com.example.blog.repository.UsersRepository;
import com.example.blog.service.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    @Transactional()
    public int join(Users user) {
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);
        try {
            usersRepository.save(user);
            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error("error : " + e.getMessage());
            LOGGER.info("회원가입에 실패하였습니다. error : " + e.getMessage());
        }
        LOGGER.info("service join -1 리턴");
        return -1;
    }

    @Override
    @Transactional
    public UsersDto updateUser(Users users) {
        Users updateUser = usersRepository.findById(users.getId())
                                            .orElseThrow(() -> {
                                                throw new IllegalArgumentException(String.format("User ID : %d를 찾을 수 없습니다.", users.getId()));
                                            });
        String encPassword = encoder.encode(users.getPassword());
        updateUser.setPassword(encPassword);
        updateUser.setEmail(users.getEmail());
        usersRepository.save(updateUser);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(updateUser.getUsername(), users.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("SecurityContex authentication 변경 완료");

        return UsersMapping.UsersConvertToDto(updateUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
        LOGGER.info("User ID:{} 삭제 완료", id);
    }

    @Override
    public OAuthToken getKakaoToken(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "2f424e16978d71f3cf8e447554a3f3ff");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String >> kakaoTokenRequest =
            new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );

        ObjectMapper mapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = mapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return oAuthToken;
    }

    @Override
    public KakaoProfile getKakaoProfile(String accessToken) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //Authorization: Bearer ${ACCESS_TOKEN}/KakaoAK ${APP_ADMIN_KEY}
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        HttpEntity<MultiValueMap<String, String >> kakaoProfileRequest =
            new HttpEntity<>(headers);

        System.out.println(kakaoProfileRequest);

        ResponseEntity<String> response1 = rt.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST,
            kakaoProfileRequest,
            String.class
        );

        ObjectMapper mapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = mapper.readValue(response1.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return kakaoProfile;
    }

    @Override
    public String kakaoRegister(KakaoProfile kakaoProfile) {
        Users users = new Users().builder()
            .username(kakaoProfile.kakao_account.getEmail() + kakaoProfile.getId())
            .email(kakaoProfile.kakao_account.getEmail())
            .password(UUID.randomUUID().toString())
            .build();
        join(users);
        return null;
    }
}
