package com.example.blog.service.impl;

import com.example.blog.entity.Users;
import com.example.blog.repository.UsersRepository;
import com.example.blog.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;


    @Override
    @Transactional
    public int join(Users user) {
        try {
            usersRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("회원가입에 실패하였습니다. error : " + e.getMessage());
        }

        return -1;
    }


}
