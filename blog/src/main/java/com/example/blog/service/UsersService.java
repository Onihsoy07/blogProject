package com.example.blog.service;

import com.example.blog.dto.UsersDto;
import com.example.blog.entity.Users;

public interface UsersService {

    int join(Users users);

    UsersDto updateUser(Users users);

    void deleteUser(Long id);

}
