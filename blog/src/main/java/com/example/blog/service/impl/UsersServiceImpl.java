package com.example.blog.service.impl;

import com.example.blog.controller.api.UserApiController;
import com.example.blog.entity.Users;
import com.example.blog.repository.UsersRepository;
import com.example.blog.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder encoder;

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


}
